#!/usr/local/bin/python3

import requests
import os.path, sys

import pyexcel as p

from _database import connection, insert_value, select_value

"""

	DATA USED 		: https://data.public.lu/fr/datasets/parkings-relais-existants/#_
	URL 			: https://download.data.public.lu/resources/parkings-relais-existants/20160602-111052/2016.06.01_Parking_relais_existants.ods
	DESCRIPTION 	: Tableau en format Open Document Spreadsheet Liste et capacité des parkings relais (P+R) existants

"""

url = "https://download.data.public.lu/resources/parkings-relais-existants/20160602-111052/2016.06.01_Parking_relais_existants.ods"
target_path = "tmp/data_parking_relais.ods"


def _download():
	''' Download xlsx file from data.public.lu '''
	global target_path, url

	if os.path.isfile(target_path):
		return True
		
	response = requests.get(url, stream=True)
	handle = open(target_path, "wb")
	for chunk in response.iter_content(chunk_size=512):
	    if chunk:  # filter out keep-alive new chunks
	        handle.write(chunk)

	if os.path.isfile(target_path):
		return True
	else:
		sys.exit("File not found")


def parse_ods():
	''' parse ods file and insert all parking infos '''
	records = p.iget_records(file_name=target_path)

	for record in records:
		if record['Localisation']:
			sql = "INSERT INTO parking_relais VALUES (NULL, %s, %s, '%s', %s, '%s')" % (
				record['Longitude'], record['Latitude'], record['Localisation'],
				record['Emplacements existants'], record['Rabattement'])
			print(sql)
			insert_value(sql)

if __name__ == '__main__':
	_download()
	parse_ods()







