## **Obligatorisk oppgave 2**
**Deloppgave 1: Prosjekt og prosjektstruktur**

*Hvordan fungerer kommunikasjonen for dere?*
Gruppen kommuniserer ofte med hverandre, og alle gruppemedlemmer deltar aktivt i planleggingen av prosjektet. Vi bruker Slack og Messenger som kommunikasjonsmidler, og ellers møtes vi personlig. På Slack avtales møter, og viktige dokumenter og lenker holdes orden på. Vi har en uformell gruppesamtale på Messenger hvor vi kan ta opp mer “casual” temaer dersom det skulle være behov for det.
Gruppemedlemmene treffes skremmende ofte utenfor planlagte tidsrom; i forelesninger enkelte av oss har felles, og tilfeldig på lesesalen hvor mange av oss tilbringer en del tid. Så vi har det enkelt for å slå av en prat om nye prosjektutfordringer også utenfor gruppemøtene og kommunikasjonsmidlene.

**Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.**
Vi hadde i utgangspunktet bestemt oss for ha et løst forhold til valg av prosjektmetodikk (tydeligvis en mislikt fremgangsmåte ifølge retteskjemaet, selv om vi ble fortalt av gruppeleder at dette var null stress. Ja, jeg er salty), men å spesielt prøve ut følgende prinsipper fra smidig utvikling: parprogrammering, scrum sprints med en slags scum master, og testdrevet utvikling.

Av disse tre prinsippene har parprogrammering klart fungert best. Vi opplever flere positive sider ved denne metoden. For det første har det fungerer bra for å oppdatere medlemmer om hvordan deler av koden de ikke har sett før fungerer. For det andre merkes det veldig godt at to hoder tenker bedre enn ett; 
vi finner gjerne flere løsninger på et problem, drøfter de ulike løsningene, og bestemmer oss for løsningen vi liker best.

Et scrum sprint skal gjerne vare i to uker til en måned. Etter vi hadde laget user stories og fordelt dem i arbeidsoppgaver, bestemte vi oss raskt for å la kommende sprint vare i kun én uke, ettersom oppgavene virket ganske simple. Dette viste seg å ikke stemme. Vi brukte mye av den første uken bare på å lære oss grunnleggende LibGDX-funksjonalitet. 
Uforutsett mye tid gikk til lesing og tutorials, 
og vi kom oss ikke raskt i gang med arbeidet. Vi bestemte oss derfor bare for å utvide sprinten med en uke (til innleveringsfristen av obligatorisk oppgave 2).
To be continued ...

I og med at vi var tidlig ute med å avtale mange møter, og prioriterte planlegging over programmering de første ukene fikk vi en veldig god sans for hvordan prosjektet skulle struktureres. Vi har valgt å samle relaterte klasser som er avhengige av hverandre. 

**Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)**
Av commithistorikken vår kan det virke som om kun noen av oss har bidratt til utviklingen av koden. Dette er ikke tilfellet, da vi i stor grad har drevet med parprogrammering. 
Vi har ikke giddet å drive med rotering av PCer vi jobber på for å tegne et kunstig bilde av bidragsmangfoldighet. Bunnlinjen her er at alle bidrar gjennom parprogrammering.


**Deloppgave 2: Krav**




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


**Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint**
1) Mer testdrevet utvikling? Har vært vanskelig å gjøre i og med at vi alle er nye til libgdx. Så utviklingen fram til nå har handlet mye om å bli kjent med systemet, 
og har primært blitt testet manuelt. 




