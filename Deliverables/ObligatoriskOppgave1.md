## **Obligatorisk oppgave 1**
**Prosjekt RoboRally - Oppstart**
Vi har fått i oppgave å lage en digital versjon av brettspillet RoboRally. I første iterasjon av produktet skal vi inkludere et spillbrett 
samt muligheten til å plassere brikker på brettet, annen spill-logikk kommer i senere iterasjoner. Mye av denne innleveringen handler om 
planlegging av oppgaven, og gruppens tanker og ønsker rundt dette. 

**Deloppgave 1: Organisering av teamet:** Alle på teamet er på andre året av utdannelsen sin innen datateknologi/datavitenskap, og har dermed tilsvarende erfaring i Java, Python og Haskell. Flere av teammedlemmene har hatt relevante prosjekter på fritiden, og har litt utvidet erfaring i visse områder, for eksempel har Alvar satt opp en egen server.

Teamet:
- **Pål**       - Teamleder
- **Eskil** 		- Kundeansvarlig
- **Alvar** 		- Utvikler
- **Øyvind** 	  - Utvikler
- **Sigve** 		- Utvikler


**Begrunnelse for valg av roller** Vi har valgt å utpeke en teamleder. Vi er enig om at det er gunstig å utpeke en som har hovedansvar for alt fra prosjektering og oppfølging til organisering av møter og booking av grupperom. Vi har behov for en leder som kan veilede resten av teamet i prinsippene om smidig utvikling, og som kan svare på spørsmål vi andre måtte ha i den sammenheng (ikke veldig ulikt en scum-master). Når det kommer til avgjørelser, kommer teamlederen til å vektlegge innspill fra resten av teamet i stor grad.

Vi inkluderer også rollen som kundeansvarlig; grensesnittet mellom kunden og utviklerne. Ettersom teamet er såpass lite, synes vi det 
er sløsing med tid å involvere hele teamet i samtale med kunden. Dette er hovedårsaken til at vi utpeker en “ambassadør”. Vi ønsker 
likevel god kontakt med kunden, for å effektivt kunne produsere et produkt som kunden er fornøyd med.

Istedenfor at et person har hovedansvaret for for eksempel tester, grafikk ellers design osv., Har vi valgt at resten av teamet skal ha 
rollen utvikler. På denne måten kan vi garantere at ingen av teammedlemmene blir sittende med en repetitiv oppgave hele prosjektet. Dette 
vil også sørge for at alle teammedlemmer kjenner til alle sider av prosjektet saker angående blant annet testing og design vil derfor 
hanskes med av hele teamet i plenum; hvor alles stemmer teller likt. 


**Deloppgave 2: Oversikt over forventet produkt:** Målet er å utvikle et Javabasert, desktop-only RoboRally-spill som er både robust, brukervennlig og velfungerende i henhold til spillets dynamikk og regler. Applikasjonen skal kjøre uavhengig av operativsystem (Windows, MacOS, Linux). Det skal være mulig å spille i Multiplayer-modus mot andre systemer som har programvaren installert, over et lokalt nettverk. 


**Liste over systemkrav for "minimal viable product"**
* Må kunne se brettet
* Brettet må kunne vise alle de forskjellige spillelementene
* Må kunne se roboten på brettet
* Må kunne plassere en brikke
* Må kunne programmere roboten
* Må kunne velge powerdown
* Roboten må kunne bevege seg
* Roboten må kunne miste liv
* Programkort må låses til registrer når roboten har mistet tilstrekkelig liv
* Roboten må kunne dø når den mister alt liv
* Roboten må kunne dø når den faller i et hull
* Roboten må ikke kunne befinne seg utenfor brettet
* Roboten må kunne plukke opp flag
* Roboten må kunne dytte andre roboter
* Roboten må kunne skyte laser
* Laserkanonene på brettet må kune skyte laser
* Samlebånd på brettet
* Samlebånd må bevege roboter
* Raske samlebånd på brettet
* Raske samlebånd må bevege roboten 2 ruter
* Hull på brettet
* Skruetrekker(reparer) på brettet
* Vegger på brettet
* Programkort
* Må ha prioritet til programkortene
* Må ha flagg på brettet
* Må kunne rotere roboten (I 90 graders intervaller)
* Må kunne kjøre på alle operativsystem
* Må kunne spilles over LAN Nettverk
* En runde består av 5 faser
* Før hver runde:
    * Del ut kort
    * Roboter blir plassert på brettet
    * Spillere lager program
* Kortene låses når runden starter
* Etter runder skal
    * Roboter repareres
    * Powerdown skje
    * Dele ut kort


**Første iterasjon av spillet**
I første iterasjon av spillet (dvs. første innlevering) ønsker vi å kun inkludere et enkelt testbrett og en spiller som kan bevege seg rundt på brettet. Brettet trenger ikke inneholde noe mer enn elementære ruter fra spillet. I denne omgang holder det også at spilleren kan beveges med piltastene. Dette er fordi vi ønsker å bli bedre kjent med LibGDX før vi bestemmer oss for et dypere designmønster. Vi vil skrive forretningslogikk som går hånd i hånd med LibGDX.


**Deloppgave 3**
**Prosjektmetodikker**
Vi ønsker være fleksible og ikke nødvendigvis knytte oss 100% til én bestemt prosjektmetodikk. Vi har valgt elementer av forskjellige prosjektmetodikker som vi tenker hjelper oss som et team oppnå målene vi har satt til de forskjellige tidsfristene. Vi har valgt å ta i bruk sprintmodellen fra Scrum, da vi ønsker å begrense mengden arbeid på prosjektbrettet slik at vi alltid arbeider med de aller viktigste elementene av prosjektet. Denne utviklingen vil vi at skal primært være testdrevet, slik at vi kan garantere robust og klar kode. Vi ønsker at hele teamet skal ha en dyp forståelse for alle delene av produktet, derfor ønsker vi å programmere i par (ihvertfall når det gjelder de viktigste elementene av prosjektet). Ved å parprogrammere øker vi også sannsynligheten for at koden vi leverer er forståelig og at teammedlemmene oppnår et godt sammarbeid.

**Organisering av møter**
Vi er enige om å møtes 2 ganger i uken, der en av gangene er gruppetimen på fredag.
I utgangspunktet møtes vi to ganger i uken, som regel mandag eller tirsdag og gruppetimen på fredag. Utenom er vi alle aktive på Slack. Før hvert møte lager en av oss (gjerne, men ikke nødvendigvis, teamleder) en event i google calander, så i beskrivelsen legger alle til temaer de ønsker å ta opp på møtet.
Som sagt har vi alle ganske lik kompetanse, så vi prøver å dele ut oppgaver etter hvert og redistribuere etter behov (tidsfrister, vanskelighetsgrad).  


**Deloppgave 4**
**Brukerhistorier**
* Som spiller ønsker jeg å kunne se spillbrettet slik at jeg kan planlegge hvor jeg kan bevege roboten min.
    * For å oppnå dette målet fulgte vi veiledningen(tutorialen) som var vedlagt prosjektet.
* Som spiller ønsker jeg å kunne se roboten dukke opp på spillbrettet, slik at jeg har oversikt over hvor jeg står og hva som er mine omgivelser.
    * For å oppnå dette målet fulgte vi veiledningen(tutorialen) som var vedlagt prosjektet.
* Som spiller ønsker jeg å kunne åpne applikasjonen uavhengig av hvilket operativsystem jeg bruker, fordi jeg vil kunne spille på Windows, Mac og Linux.
    * For å oppnå dette målet fulgte vi veiledningen(tutorialen) som var vedlagt prosjektet. Applikasjonen burde kjøre overalt siden vi bruker programmeringsspråket Java.

Vi har ikke lagt skikkelig vekt på brukerhistorier i denne innleveringen, siden kodingen stort sett bestod av å følge veiledningen fra MittUiB. For å oppfylle disse brukerhistoriene fullstendig burde vi ha laget forretningslogikk med brett og plassering osv. i tillegg til LibGDX/UI-delen. Som allerede påpekt valgte vi å vente med dette. Vi kommer nok til å ta opp de samme brukerhistoriene på neste møte og videreføre dem.
Angående den siste brukerhistorien har vi ikke prøvd å kjøre applikasjonen på Mac, da ingen av oss har det tilgjengelig. Dette var egentlig ikke høyt oppe på prioriteringlisten vår i denne omgang.
    

**Retrospektiv**
- Vi synes parprogrammering fungerte overraskende bra. Da vi fulgte LibGDX-tutorialen hadde vi en PC med IntelliJ oppe i tillegg til en annen med selve tutorialen. Spesielt hjalp det med å skjønne hva alle de ulike objektene var, og hvordan de jobbet sammen.
- Vi synes det har fungert bra med mange møter, vi vil heller møtes ofte og kort enn sjeldent og lange. Av erfaring dør produktiviteten om vi blir sittende for lenge.
     - Det er også veldig greit å ha en agenda til hvert møte. Derfor satt vi opp kalenderen, som nevnt.
- Det er mange ting vi ikke har fått prøvd helt ordentlig enda, så er litt vanskelig å si hvordan vi liker det. Eksempelvis fikk vi egentlig ikke testet ut brukherhistorier noe særlig.

Videre vil vi få orden på hvordan vi skal knytte LibGDX opp mot spillogikken. Vi er enige om å skjekke ut Scene2D, da det tydeligvis skal integreres godt sammen med LibGDX.
























