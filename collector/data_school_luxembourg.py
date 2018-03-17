#!/usr/local/bin/python3

import urllib
import os.path, sys, time

from bs4 import BeautifulSoup

from _database import connection, insert_value, select_value, select_values


import googlemaps

gmaps = googlemaps.Client(key='')

"""

	DATA USED 		: googleMAPS/
	DESCRIPTION 	: Website

"""

url = "http://citysavvyluxembourg.com/expat-essentials/private-international-schools-list/"

def PagetoSoup(url): # Mise en beauté de la page
	try:
		response = urllib.request.urlopen( url )
	except urllib.error.HTTPError as e:
		print("HTTPError with: ", url, " as ", e)
		return None

	return BeautifulSoup(response.read(), "lxml")

def get_address_location(address):
	''' Return the lattitude and longitude of the current city '''

	geocode_result = gmaps.geocode(address)

	lat = geocode_result[0]['geometry']['location']['lat']
	lng = geocode_result[0]['geometry']['location']['lng']

	return lat, lng


def search_school_address(place_searchable):
	''' Return the lattitude and longitude of the current city '''

	# geocode_result = gmaps.places("Lycee Eischen, Luxembourg")
	# geocode_result = gmaps.places("École primaire Luxembourg")
	# geocode_result = gmaps.places("university Luxembourg")
	geocode_result = gmaps.places(place_searchable)
	time.sleep(1)

	for v in geocode_result['results']:
		print("------")
		lat = v['geometry']['location']['lat']
		lng = v['geometry']['location']['lng']
		types = v['types']
		school_name = v['name']
		address = v['formatted_address']

		# if not 'school' in types: university
		# 	continue

		if 'school' in types and 'luxembourg' in address.lower():
			sql = "INSERT INTO schools VALUES (NULL, '%s', %s, %s)" % (school_name, lng, lat)
			print(sql)
			insert_value(sql)

		print(address, types, lat, lng)


def crap_google_map_from_cities():
	sql = "SELECT id, name FROM cities WHERE id < 120 ORDER BY id ASC;"
	cities = select_values(sql)

	for city in cities:
		print(city)
		search_school_address("Lycee " + city['name'] + " Luxembourg")



def get_from_site():
	page = PagetoSoup(url)
	schools = page.find_all('p', style = 'padding: 2px 6px 4px 6px; color: #555555; border: #dddddd 2px solid;')
	for school in schools:
		print("-------------------")
		#print(school.split("<br/>"))

		school_name = school.strong.string
		address = str(school).split("<br/>")[1]
		lat, lng = get_address_location(address)

		sql = "INSERT INTO schools VALUES (NULL, '%s', %s, %s)" % (school_name, lng, lat)
		print(sql)
		insert_value(sql)


if __name__ == '__main__':
	#get_from_site()
	crap_google_map_from_cities()







