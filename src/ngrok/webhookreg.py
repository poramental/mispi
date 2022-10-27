import pathlib
import requests
from pathlib import Path



def getLinkAndToken():
    path = Path(pathlib.Path.cwd(),'src','main','resources','application.properties')
    print(str(path))
    paramlist = []
    with open(path) as file:
        for line in file:
            if 'server.port' in line or 'bot.name' in line:
                continue
            
            paramlist.append(str(line.strip('\n').split('=')[1].strip()))
           
    return paramlist  

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
        print(response.text)
        print("не зарегано")
    else:
        print(response.text)
        print('непонял ошибку')

def main():

    token = getLinkAndToken()[0]
    link = getLinkAndToken()[1]
    

    regTelegramWebHook(link,token)
    
            
           
    

if __name__ == "__main__":
    main()