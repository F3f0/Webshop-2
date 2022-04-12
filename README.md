### Webshop  ###

### Inlämningsuppgift – JAVA21 – G/VG ###

Ni ska skapa en webshop som man kan använda genom ett REST API. Ni ska alltså inte skapa en frontend eller annan klient som använder denna tjänst. 
Det räcker med vanliga rest endpoints i Spring boot, precis som ni lärt er tidigare i denna kurs.
Genom denna uppgift har ni möjlighet att få både G och VG.

Eran uppgift är nu att skapa en webshop i spring boot som ”exponeras” eller används genom att ni skapar ett REST api som användarna kan använda. 
Denna webshop ska ha stöd för att lägga till nya varor i eran webshop, lista alla befintliga varor som finns, skapa användare, lista alla användare, 
köpa varor och se en historik över alla köp. Notera att användarna används bara för att knyta ihop ”ordrar”, som består av en vara och en användare. 
Man ska alltså inte logga in eller göra något annat som användare.

## För G ska samtliga av dessa punkter vara uppfyllda i ert program:

  # För GET ska ni ha följande endpoints:
    • http://localhost:8080/items (Denna returnerar alla varor)
    • http://localhost:8080/items/:id(Denna returnerar en vara baserat på varans namn. Denna endpoint ska använda path params)
    • http://localhost:8080/customers (Denna returnerar alla kunder)
    • http://localhost:8080/customers/:id (Denna returnerar en kund baserat på id. Denna endpoint ska använda path params)
    • http://localhost:8080/orders(Denna returnerar alla köp)
    • http://localhost:8080/orders/:customerId (Denna returnerar alla köp för en kund baserat på kundens id)
     
  # För POST ska ni ha följande endpoints:
    • http://localhost:8080/customers (Denna endpoint skapar en ny kund)
    • http://localhost:8080/items(Denna endpoint skapar en ny vara)
    • http://localhost:8080/items/buy?customer=customerId&item=itemName (Denna endpoint gör ett nytt köp för en specifik kund och en specifik vara baserat på id)
  
## För VG ska ni ha klarat av kriterierna för G och sen utöka programmet med dessa punkter:

    • Utöka eran kod och lägg till enhetstester för controller lagret. Här ska ni mocka upp en fejkdatabas så att ni har något att testa emot.
    • Alla endpoints i era controllers ska testas för VG Redovisning/inlämning
  
## Denna uppgift ska lämnas in måndagen den 25e april 2022-04-25 klockan 16:00. Ingen presentation behövs för denna inlämning. 

  Inlämningen ska innehålla ett kort word dokument med följande punkter:
    • Vilka ni är i gruppen
    • Utmaningar som ni haft under arbetets gång
    • Vad ni har lärt er
    • Länk till det github repository som ni använde för projektet

  Ni ska ladda upp ett word dokument per grupp på inlämningsuppgifter på kursen i studentportalen.
  Lycka till!
