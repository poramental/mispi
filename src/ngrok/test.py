import requests


resp = requests.post("http://localhost:5000/mispi",data = {"mispiNum": 431231, "errorCode": 21})
print(resp.status_code)