## **Obligatorisk oppgave 2**
**Deloppgave 1: Prosjekt og prosjektstruktur**

*Hvordan fungerer rollene i teamet?*

I praksis har vi stort sett ikke merket til rollebesetningen. Det viser seg at alle på gruppen er flinke til å ta initiativ til møter, delege av oppgaver etc, så vi har ikke ligget spesielt merke til at vi har en teamleder. Kundekontakten vår har ikke vært i kontakt med kunden, siden vi ikke har hørt noe eksplisitt innkalling fra kunden. Likevel kommer antageligvis dette til å endre seg etterhvert som prosjektet øker i kompleksitet. Derfor velger vi å beholde rollene akkurat slik de er nå.

*Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.*

Vi vurderer å utnevne en *grafisk designer* som skal være ansvarlig for design av egentilpasset tilesheets for spillbrettet og for spillerne (roboter). Den som har denne rollen vil også styre hvordan menyen vil se ut senere. Vi venter forøvrig med dette, da vi ikke har kommet langt nok i utviklingsprosessen for at dette skal bli relevant.
 
*Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne?*

Vi har erfart at bruk av prosjekt-brettet fra scrum er veldig praktisk når det kommer til å holde oversikt over hva alle de andre på gruppen jobber med, samt å finne nye oppgaver en selv kan jobbe med. 
Teamet er svært effektive under sprints og får gjort svært mange punkter på prosjekt-brettet.


*Hvordan fungerer kommunikasjonen for dere?*

Gruppen kommuniserer ofte med hverandre, og alle gruppemedlemmer deltar aktivt i planleggingen av prosjektet. Vi bruker Slack og Messenger som kommunikasjonsmidler, og ellers møtes vi personlig. På Slack avtales møter, og viktige dokumenter og lenker holdes orden på. Vi har en uformell gruppesamtale på Messenger hvor vi kan ta opp mer “casual” temaer dersom det skulle være behov for det.
Gruppemedlemmene treffes skremmende ofte utenfor planlagte tidsrom; i forelesninger enkelte av oss har felles, og tilfeldig på lesesalen hvor mange av oss tilbringer en del tid. Så vi har det enkelt for å slå av en prat om nye prosjektutfordringer også utenfor gruppemøtene og kommunikasjonsmidlene.
Vi forsøker også å ha en åpen holdning til spørsmålsstilling om hvordan koden fungerer og henger sammen, og også kritikk av hverandres kodedesign, navngiving, mm. Lurer en av oss på hvordan koden en annen skrev fungerer, er det bare å spørre. Vi er alle opptatt av at alle skal forstå mest mulig av koden. Vi er enige om at spesielt kritikk kan være utfordrende, men nødvendig for å oppnå best mulig helhetlig design. Da må dette naturligvis gjøres på en konstruktiv måte. Nå har vi ikke opplevd noe særlig til akkurat dette, men vi er enige om at det bør være en mulighet.

*Hvordan fungerer gruppedynamikken?*

Dynamikken i gruppen er bra. Vi kommuniserer enkelt med hverandre, både over nett og når vi snakker sammen. Støter vi på en utfordring vi sliter å få til, tar vi gjerne kontakt med andre gruppemedlemmer om råd. Eventuelt tar vi opp sånne ting i plenum på gruppemøtene. I så fall er alle villige til å komme med innspill for å komme frem til best mulig løsning. Vi liker å løse utfordringer sammen, og det gir også ofte det beste resultatet. Ellers er stemningen lett og uformell, og vi trives i hverandres selskap. 

**Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.**

Vi hadde i utgangspunktet bestemt oss for ha et løst forhold til valg av prosjektmetodikk , men å spesielt prøve ut følgende prinsipper fra smidig utvikling: parprogrammering, scrum sprints med en slags scrum master, og testdrevet utvikling.

Av disse tre prinsippene har parprogrammering klart fungert best. Vi opplever flere positive sider ved denne metoden. For det første har det fungert bra for å oppdatere medlemmer om hvordan deler av koden de ikke har sett før fungerer. For det andre finner vi gjerne flere løsninger på et problem, drøfter de ulike løsningene, og bestemmer oss for løsningen vi liker best.

Etter vi hadde laget user stories og fordelt dem i arbeidsoppgaver, bestemte vi oss raskt for å la kommende sprint vare i kun én uke, ettersom oppgavene virket ganske simple. Dette viste seg på en måte å ikke stemme. Vi brukte mye av den første uken bare på finne ut hvordan vi skulle sammenkoble LibGDX og forretningslogikk. Uforutsett mye tid gikk til lesing og tutorials, 
og vi kom oss ikke raskt i gang med selve kodingen. Vi bestemte oss derfor bare for å utvide sprinten med en uke. Da klarte vi å bli ferdig med arbeidsoppgavene med god margin.
Et forbedringspotensiale i denne sammenheng: vi bør i neste sprint på forhånd bestemme oss for hvem som skal gjøre hvilke arbeidsoppgaver, i stedet for at noen bare tar første og beste oppgave. Slik får vi bedre kontroll og oversikt over hva alle medlemmer skal gjøre i denne omgangen.

Testdrevet utvikling har vi egentlig forsøkt ut i liten grad. De viktigste klassene som har kommet frem under denne sprinten er avhengig av å instansiere et TiledMap, noe som gir NullPointerException når det gjøres i test-klassene. Dermed har ikke det vært mulig å lage sentrale tester. !!!! Insert her noe om at det kommer vi til å fikse, aka forbedringspotensiale ;)

I og med at vi var tidlig ute med å avtale mange møter, og prioriterte planlegging over programmering de første ukene fikk vi en veldig god sans for hvordan prosjektet skulle struktureres. Vi har valgt å samle relaterte klasser som er avhengige av hverandre. 

**Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)**
Av commithistorikken vår kan det virke som om kun noen av oss har bidratt til utviklingen av koden. Dette er ikke tilfellet, da vi i stor grad har drevet med parprogrammering. 
Vi har ikke giddet å drive med rotering av PCer vi jobber på for å tegne et kunstig bilde av bidragsmangfoldighet. Bunnlinjen her er at alle bidrar gjennom parprogrammering.


**Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint**
1) Mer testdrevet utvikling? Har vært vanskelig å gjøre i og med at vi alle er nye til libgdx. Så utviklingen fram til nå har handlet mye om å bli kjent med systemet, 
og har primært blitt testet manuelt. 

**Deloppgave 2: Krav**

**Brukerhistorier**
* Som spiller ønsker jeg å ha medspiller på brettet, slik at jeg har noen å spille med.
    * Arbeidsoppaver
        * Få på plass enda et Player-objekt som representerer den nye spilleren
        * Kunne lage flere unike Player-objekt som representerer en ny spiller
        * InputHandler for Spillklassen som håndterer inputs for alle de forskjellige playersene
    * Akseptansekrav:  Hvis jeg starter spillet skal jeg se en annen spiller på kartet
* Som spiller ønsker jeg at jeg kan dytte et robot på kartet i den retningen jeg beveger meg, slik at roboter ikke kan stå oppå hverandre.
    * Arbeidsoppgaver
        * Klasse som sørger for at dersom en spiller beveger seg mot en annen spiller, så beveges begge spillerene i den retningen
    * Akseptansekrav: Hvis jeg prøver å bevege meg inn i en robot, skal roboten bevege seg i samme retning som roboten min beveger seg. Dette gjelder også hvis flere roboter står på rekke. De skal heller ikke bli dyttet hvis de treffer en vegg.
* Som spiller ønsker jeg at det skal være vegger på kartet, slik at roboten min blir stoppet om den forsøker å bevege seg der.
    * Arbeidsoppgaver
        * Generell klasse for alle slags mulige tiles på det statiske brettet
        * Veggobject (med rotasjon?)
        * En eller annen klasse som håndterer kollisjon
    * Akseptansekrav: Dersom roboten min beveger seg ut av brettet så skal roboten min bli plassert på chechpointet sitt.

**Forklar kort hvordan dere har prioritert oppgavene fremover.**

Vi har prioritert oppgaver som er direkte knyttet med spillbrettet og robotens bevegelse. Det vil si oppgaver som angår funksjonalitet. På kanban brettet har vi fortsatt en del oppgaver knyttet til dette som vi må håndtere før vi kan begynne på nye oppgaver. Etter dette vil vi håndtere oppgaver som programkort og faser og runder.


**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.**
Fram til nå har vi prioritert oppgaver direkte knyttet til robotenes bevegelse. Vi har kommet godt i gang med dette og har allerede implementert funksjonalitet som:

- backUp
- Kan falle ut av banen, og respawne på backup
- Vegger (i alle retningen og kombinasjoner av dem)
- Kan dytte roboter 
    - Roboter kan ikke dytter gjennom vegger

Vi har valgt å legge alt av grafikk til side for denne innleveringen, og heller prioritere kode-aspektene av oppgaven.

**Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**
Så vidt vi vet finnes det for øyeblikket ingen bugs. Det er litt vanskelig å si hva som klassifiseres som bugs, i og med at prosjektet ikke er ferdig implementert. Vi har valgt å se på bugs som “uventet adferd”, noe vi ikke har funnet noe av enda.


**Deloppgave 3: Kode**

*Testing*

Vi har laget et enviroment for manuelle tester

*Manuelle tester:*

1) Bruker piltastene til å teste robotens bevegelse

    a. Der pil-opp refererer til å bevege seg framover
    
    b. Pil-ned beveger seg bakover
    
    c. Pil-høyre roterer roboten mot høyre
    
    d. Pil-venstre roterer roboten mot venstre.
    
2) Roter spilleren direkte mot en vegg. Dersom det er en:

    a. Vegg på venstre side og du beveger deg mot venstre bør roboten ikke beveges
    
    b. Vegg på høyre side og du beveger deg mot høyre bør roboten ikke beveges
    
    c. Vegg over roboten og du beveger deg oppover bør roboten ikke beveges
    
    d. Vegg under roboten og du beveger deg nedover bør roboten ikke beveges
    
3) Plasser spilleren direkte til høyre eller venstre for en av de andre robotene på banen, beveg deg så mot roboten. Forventet resultat vil være at begge robotene beveger seg i samme retnings som piltasten.

4) Plasser spilleren direkte over eller under begge de to andre robotene på banen. Beveg deg så mot robotene. Det forventede resultatet vil være at begge robotene beveger seg i samme retning som piltasten

5)  Repeter test 4, men plasser alle robotene inntil en vegg. Beveg deg nå mot robotene. Det forventede resultatet vil være at ingen av robotene beveger seg.



**Møtereferat**
*21.02.2020 (Alle gruppemedlemmer deltok)*

Dropper Scene2D for å representerer player, ettersom det blir problematisk med koordinatsystemet. Vi måtte i så fall forholde oss til pixler, og det blir bare rot fant vi ut av.
Vi tar nå utgangspunkt i Libgdx tutorialen.

Vi gikk gjennom rutinene våre for 1. oblig, og fant mye vi kan forbedre/endre
* Dokumentere absolutt alt vi gjør
* Fokusere møtene våre på å få konkrete arbeidsoppgaver som vi kan jobbe med
* Dersom vi utelater å gjøre noe / noe blir for vanskelig for oss å gjøre så må vi skrive forklaringer for det.
* Fokus på å få satt opp en solid kodebase slik at det blir lettere for individene å jobbe med prosjektet

Vi har laget brukerhistorier og arbeidskrav, og lagt arbeidskravene 
inn i project board. Så har vi parprogrammert (triprogrammert?) og kravet om å plassere enda en player på kartet.

*25.02.2020 (Alle gruppemedlemmer deltok)*

Siden flere var borte forrige uke gikk vi gjennom koden i plenum, 
og fikk alle up to speed på oblig 2, og hva vi må gjøre annerledes (flere tester f.eks)  og hvordan vi skal komme videre med koden. Etter møtet skled vi naturlig inn i en slags sprint og f
ikk gjort mange av punktene på prosjekt-brettet.

*26.02.2020 (Alle gruppemedlemmer deltok)*

Vi jobbet videre med kodingen, siden flere jobbet med ulike deler snakket vi en del sammen. 
En bad merge oppstod og ble pushet til master, slik at produktet krasjet ved oppstart. Dette ble raskt fikset i plenum, men vi kjente alle litt på 
(og hadde litt godt av) panikken som følger av en bad merge.

*28.02.2020 (Pål, Eskil, Alvar og Øyvind)*

Vi arbeidet med å fullføre den andre obligatoriske innleveringen.


**Deloppgave 2: Krav**

*For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester*

* Som spiller ønsker jeg å ha medspiller på brettet, slik at jeg har noen å spille med.
   	Arbeidsoppgaver:
    * Få på plass enda et Player-objekt som representerer den nye spilleren
    * Kunne lage flere unike Player-objekt som representerer en ny spiller
    * InputHandler for Spillklassen som håndterer inputs for alle de forskjellige playersene
    
    Akseptansekrav: Hvis jeg starter spillet skal jeg se en annen spiller på kartet

* Som spiller ønsker jeg at jeg kan dytte et robot på kartet i den retningen jeg beveger meg, slik at roboter ikke kan stå oppå hverandre.
  	Arbeidsoppgaver:
    * Klasse som sørger for at dersom en spiller beveger seg mot en annen spiller, så beveges begge spillerne i den retningen
    
    Akseptansekrav: Hvis jeg prøver å bevege meg inn i en robot, skal roboten bevege seg i samme retning som roboten min beveger seg. Dette gjelder også hvis flere roboter står på rekke. De skal heller ikke bli dyttet hvis de treffer en vegg.

* Som spiller ønsker jeg at det skal være vegger på kartet, slik at roboten min blir stoppet om den forsøker å bevege seg der.
  Arbeidsoppgaver:
    * Generell klasse for alle slags mulige tiles på det statiske brettet
    * Veggobject (med rotasjon?)
    * En eller annen klasse som håndterer kollisjon
    
    Akseptansekrav: Hvis roboten min beveger prøver å bevege seg inn i en vegg, skal den ikke bevege seg.


* Som spiller ønsker jeg at roboten min plasseres på checkpoint dersom den beveger seg ut av brettet, slik at den ikke står plassert ulovlig.
  Arbeidsoppgaver:
    * Playerklassen må ha en origin som den kan respawne på
    * Klasse som skjekker om bevegelsen tar spiller ut av kartet, og som flytter spilleren til spawn
    
    Akseptansekrav: Dersom roboten min beveger seg ut av brettet så skal roboten min bli plassert på chechpointet sitt. 


*Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.*

* Som spiller ønsker jeg at roboten min skal rotere dersom roboten stiller seg på et tannhjul
 Arbeidsoppgaver: 
    * Akseptansekrav: Dersom roboten stiller seg på et tannhjul seg roboten rotere i samme retning som tannhjulet

* Som spiller ønsker jeg at roboten min skal bevege seg i retningen av et samlebånd dersom den stiller seg på et samlebånd
    * Akseptansekrav: Dersom en robot stiller seg på et samlebånd skal den bevege seg i retningen av samlebåndet


**Forklar kort hvordan dere har prioritert oppgavene fremover.**

Vi har prioritert oppgaver som er direkte knyttet med spillbrettet og robotens bevegelse. Det vil si oppgaver som angår funksjonalitet. På scrumbrettet har vi fortsatt en del oppgaver knyttet til dette som vi må håndtere før vi kan begynne på nye oppgaver. Etter dette vil vi håndtere oppgaver som programkort og faser og runder.


*Forklar kort hvilke hovedkrav dere anser som en del av MVP og hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?*

Vi anser fortsatt MVP som det absolutte minimum av funksjonalitet som vi trenger for at spillet kan fungere, MVP er da:
* Et Kart som inneholder alle spill-objektene vi trenger for å spille RoboRally. Kartet må altså inneholde:
    * Vegger
    * Laser
    * Flag
    * Hull
    * Tannhjul
    * Transportbånd
    * Skiftenøkkel
    * Roboter
* En overordnet spill-klasse som behandler spill-logikk og viser kartet grafisk til brukeren.
* En Robot som kan
    * Bevege seg
    * Miste liv
    * Dø
    * Gjennoppstå
    * Powerdown
* En spiller som kan:
    * Få utdelt programkort
    * Legge ned et program
    * Tape/vinne
   	





