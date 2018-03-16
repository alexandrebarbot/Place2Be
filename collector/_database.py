import pymysql.cursors

# Connect to the database
connection = pymysql.connect(host='IP', user='USERNAME', password='PASSWORD', db='P2B', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)

def insert_value(sql):
	''' Insert Into dqtqbqse'''
	try:
	    with connection.cursor() as cursor:
	        # Create a new record
	        cursor.execute(sql)
	        new_id = cursor.lastrowid

	    # Commit to save the data
	    connection.commit()

	except:
	    return False

	return new_id

def select_value(sql):
	try:
	    with connection.cursor() as cursor:
	        # Read a single record
	        cursor.execute(sql)
	        result = cursor.fetchone()
	except:
	    return False

	return result




