import requests
import json


def regTelegramWebHook(linkFromNgrok,token):
    link = "https://api.telegram.org/bot"+token+"/setwebhook?url="+linkFromNgrok+"/bots/index.php"
    print(link)
    response = requests.get(link)
    valueslist = []
    for item in response.json().values():
        valueslist.append(item)
        
    if valueslist[0]:
        print("удачненько зарегались")
    elif not valueslist[0]:
        print("не зарегано")
    else:
        print('непонял ошибку')
def main():
    token = input("bot token ->")
    linkFromNgrok = input("link from ngrok ->")
    regTelegramWebHook(linkFromNgrok , token)
    
            
           
    

if __name__ == "__main__":
    main()