#!/usr/local/bin/python3

import urllib
import os.path, sys

from bs4 import BeautifulSoup

from _database import connection, insert_value, select_value


import googlemaps

gmaps = googlemaps.Client(key='AIzaSyDY-RQTqP69YXusm4aHUJAb47HsAy2unDg')

"""

	DATA USED 		: http://citysavvyluxembourg.com/expat-essentials/private-international-schools-list/
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

def run():
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
	run()







