#!/usr/local/bin/python3

import urllib
import os.path, sys, re

from bs4 import BeautifulSoup

from _database import connection, insert_value, select_value

import googlemaps

gmaps = googlemaps.Client(key='AIzaSyDY-RQTqP69YXusm4aHUJAb47HsAy2unDg')

"""

	DATA USED 		: https://data.public.lu/fr/datasets/prix-annonces-des-logements-a-luxembourg-ville-par-quartier/#_
	DESCRIPTION 	: Prix annoncés des logements à Luxembourg selon les quartiers, sur les 12 derniers mois

"""

url_ = "http://observatoire.liser.lu/basedeprix_fiche.cfm?comid=%s"

def PagetoSoup(url): # Mise en beauté de la page
	try:
		response = urllib.request.urlopen( url )
	except urllib.error.HTTPError as e:
		print("HTTPError with: ", url, " as ", e)
		return None

	return BeautifulSoup(response.read(), "lxml")

def get_address_location(town):
	''' Return the lattitude and longitude of the current city '''

	geocode_result = gmaps.geocode(town + " Luxembourg")
	print(geocode_result)

	lat = geocode_result[0]['geometry']['location']['lat']
	lng = geocode_result[0]['geometry']['location']['lng']

	return lat, lng

def run():
	''' '''
	global url

	# For each town
	for x in range(119, 128):
		url = url_ % x
		page = PagetoSoup(url)

		# <h1 class="titre">Mersch</h1>
		town = page.find('h1', class_ = 'titre').string
		if town is None:
			continue

		town = town.encode('ascii','ignore').decode("utf-8") 

		## Find town ID
		sql = "SELECT id FROM cities WHERE name = '%s'" % town
		tpl = select_value(sql)

		if tpl:
			town_id = tpl['id']
		else:
			lat, lng = get_address_location(town)
			sql = "INSERT INTO cities VALUES (NULL, \"%s\", %s, %s)" % (town, lat, lng)
			print(sql)
			town_id = insert_value(sql)

			if not town_id:
				sys.exit("No town, exit")

		# <table cellspacing="2" class="tabstat" summary="Tableau d'information sur les prix"><tbody>
		price_table = page.find_all('table', summary = 'Tableau d\'information sur les prix')[-1]

		# find all <td class="colnombre">
		# Messy table - TD 3, 5, 9, 11 needed
		tds = price_table.find_all('td', class_ = 'colnombre')
		prices = {
			'moy_price_flat': re.sub("[^0-9]", "", tds[2].string),
			'moy_price_square_meter_flat': re.sub("[^0-9]", "", tds[4].string),
			'moy_price_house': re.sub("[^0-9]", "", tds[8].string),
			'moy_price_square_meter_house': re.sub("[^0-9]", "", tds[10].string)
		}

		sql = "INSERT INTO real_estate_price VALUES (NULL, %s, %s, %s, %s, %s)" % (
			town_id, prices['moy_price_flat'] if prices['moy_price_flat'] else 0, prices['moy_price_square_meter_flat'] if prices['moy_price_square_meter_flat'] else 0,
			prices['moy_price_house'] if prices['moy_price_house'] else 0, prices['moy_price_square_meter_house'] if prices['moy_price_square_meter_house'] else 0)
		print(x, sql)
		insert_value(sql)

if __name__ == '__main__':
	run()















