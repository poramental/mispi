import pathlib
import requests
from pathlib import Path



def getLinkAndToken():
    path = Path(pathlib.Path.cwd(),'mispibot','src','main','resources','application.properties')
    print(str(path))
    paramlist = []
    with open(path) as file:
        for line in file:
            if 'bot.path' in line or 'bot.token' in line:
                paramlist.append(str(line.strip('\n').split('=')[1].strip()))


    print(paramlist)     
    return paramlist  

def regTelegramWebHook(linkFromNgrok,token):
    link = "https://api.telegram.org/bot"+token+"/setwebhook?url="+linkFromNgrok+"/bots/index.php"
    print(link)
    response = requests.get(link)
    valueslist = []
    for item in response.json().values():
        valueslist.append(item)
        
    if valueslist[0]:
        print("удачненько зарегались 👍")
    elif not valueslist[0]:
        print(response.text)
        print("не зарегано 👎")
    else:
        print(response.text)
        print('непонял ошибку☹️☹️')

def main():

    linkAndToken = getLinkAndToken()
    link = 0
    token = 0
    for item in linkAndToken:
        if 'https' in item:
            link = item
        else:
            token = item

    regTelegramWebHook(link,token)
    
            
           
    

if __name__ == "__main__":
    main()