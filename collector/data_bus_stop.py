#!/usr/local/bin/python3

import requests, time
import os.path, sys

from _database import connection, insert_value, select_value

"""

	DATA USED 		: https://data.public.lu/fr/datasets/arrets-de-transport-public-et-departs-en-temps-reel/#_
	URL 			: http://travelplanner.mobiliteit.lu/hafas/query.exe/dot?performLocating=2&tpl=stop2csv&look_maxdist=150000&look_x=6112550&look_y=49610700&stationProxy=yes
	DESCRIPTION 	: API qui permet de connaître la liste et localisation des arrêts de transport public.


"""

url = "http://travelplanner.mobiliteit.lu/hafas/query.exe/dot?performLocating=2&tpl=stop2csv&look_maxdist=150000&look_x=6112550&look_y=49610700&stationProxy=yes"

def _download():
	''' Download bus data from data.public.lu '''
	global target_path, url

	response = requests.get(url, stream=True)

	return response.text

def parse_bus_data():
	data = [x for x in _download().split('\n') if x]

	timer = 0
	for bs in reversed(data):
		bs = bs.split("@")

		# Remove #= from the bus line
		for (i, item) in enumerate(bs):
			bs[i] = item.split("=")[-1]

		# Insert into the databse
		sql = "INSERT INTO bus_stop VALUES (NULL, '%s', %s, %s) ON DUPLICATE KEY UPDATE stop_name = stop_name;" % (bs[1], bs[3].replace(',', '.'), bs[2].replace(',', '.'))
		print(sql)
		insert_value(sql)

		if timer > 100:
			time.sleep(5)
			timer = 0
		else:
			timer += 1

if __name__ == '__main__':
	parse_bus_data()







