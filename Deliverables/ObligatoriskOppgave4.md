# Obligatorisk oppgave 4

## Deloppgave 1: Team og prosjekt

## Roller i teamet

Sånn rent rollemessig er situasjonen ganske så lik som den alltid har vært. De rollene vi engang tildelte to av oss (teamleder og kundekontakt) har i praksis ikke gjort noe ut av seg.

I startfasen av prosjektet bestemte vi oss for at teamlederen skulle ha hovedansvaret for innkalling til møter og være beslutningstaker der det oppstod uenighet eller uklarhet. For det første har vi variert veldig på hvem som foreslår og innkaller neste møte. I praksis har den som føler behov for et møte gitt en lyd i gruppechatten på Messenger, og så har vi avtalt det i plenum derfra. For det andre har vi stort sett ikke møtt på en situasjon hvor vi skulle trengt en avgjørende stemme for å komme oss videre. Ingen av oss har veldig sterke oppfatninger om hvordan ting bør gjøres, så vi har egentlig ikke støtt på noen særlige konflikter innad i gruppen (heldigvis!). Dette er grunnen til at teamlederrollen har forblitt så irrelevant for vårt arbeid og progresjon.

Ettersom det verken ikke var obligatorisk å ha en samtale med kunden/emneansvarlig, og vi aldri følte behovet for noen ekstern input fra emneansvarlig, fikk vi heller aldri bruk for kundekontakt-rollen.

Vi følte aldri noe behov for å oppdatere hvem som er teamleder og kundekontakt, siden vi i praksis ikke bruker rollene til noe som helst.

## Refleksjon: Erfaringer og valg

Vi har gjennom hele prosjektet vært enige om at vi skulle få til multiplayer over LAN, selv om dette ikke var en del av MVP. Dermed ønsket vi ikke å prioritere utviklingen av en interessant AI. Av diverse grunner (diskutert i detalj under retrospektet) kom vi oss ikke i gang med denne oppgaven før én uke før siste innlevering. Optimistisk nok bestemte vi oss for å prøve å få det til. Det viste seg å være mye mer jobb involvert enn det vi hadde sett for oss. Selv om vi fikk til å opprette en lobby som &quot;host&quot; og få koblet seg på denne lobbyen, fungerer ikke multiplayer-delen (den viktigste delen!) rett og slett pga. vi ikke kom i mål. Det tok oss overraskende lang tid å få til tilkobling mellom to PCer over samme nettverk, og den utvikleren av oss som hadde hovedansvaret for multiplayer og nettverk hadde kun tilgang på én PC, så vi fikk ikke testet det skikkelig underveis heller.

Så til syvende og sist ble vi sittende igjen med et prosjekt hvor Multiplayer ikke støttes og hvor Singleplayer spilles mot dumme AIs som velger tilfeldige trekk.

Klisjéaktig nok er jo en sentral del av prosjektarbeid nettopp å oppleve nederlag og at det ferdigstilte produktet ikke blir slik en hadde sett for seg i utgangspunktet. Sånn sett er vi ikke bare lei oss for at vi ikke kom oss i mål. Vi forsøker å se på det som noe positivt som kan læres fra, men selvfølgelig oppleves det også som litt surt.

## Retrospektiv

Vi begynner dette retrospektet med å fortelle om diverse hendelser og forhold som satte en stopper for progresjonen og motivasjonen til gruppen, da det er helt sentralt for diskusjonen vår.

Forrige innlevering i slutten av mars representerte slutten på sprinten vår. Den gang hadde vi en ambisjon om å komme oss kjapt i gang med nye sprinter, for å bruke tiden mest mulig effektivt. Vi kom kjapt i gang med formuleringen av brukerhistoriene, men det skulle ta oss (veldig) lang tid å faktisk begynne på arbeidsoppgavene.

For det første brukte vi flere møter bare på å formulere brukerhistoriene med de tilhørende arbeidsoppgavene og akseptansekravene. Vi ble nok veldig ambisiøse og prosjekterte for mye arbeid for én 2 ukers sprint (men alt var likevel innenfor MVP).

For det andre hadde vi et uhell hvor én på gruppen klarte å slette dokumentet med alle brukerhistoriene, før vi fikk lagt oppgavene inn i project boardet på GitHub. Dette skjedde i påsken, hvor de fleste på gruppen hadde tatt seg pause fra prosjektet. De andre gruppemedlemmene tok denne hendelsen helt fint, for uhell skjer jo. Men det gjorde det vanskelig å komme tilbake til arbeidet igjen; det var en stund siden sist, og selv om arbeidsoppgavene var noenlunde klare for alle (vi hadde jo tross alt skrevet dem sammen) visste ikke folk helt nøyaktig hva som trengtes å gjøre eller hvem som skulle gjøre hva. Vi var vant til å ha god oversikt over hva som trengs å gjøre og hvem som gjør hva ved hjelp av GitHub issues med assignees. Nå var det mer kaos, og alle var enige om å bare reformulere arbeidsoppgavene fortest mulig slik at vi kunne komme oss i gang igjen. Dette tok lang tid: ingen hadde motivasjon nok til å skrive det på fritiden, så vi skrev kun i plenum på møter (brakkesyken hadde sin virkning på oss). Alt i alt tok det oss tre uker fra vi begynte sprinten til vi hadde ferdigstilte arbeidsoppgaver.

I ettertid så får det en til å tenke: vi burde jo ha klart å håndtere den situasjonen bedre? Det hadde vært ferie og vi hadde møtt motgang, greit nok, men noen burde da kunne kommet seg i gang med enkelte arbeidsoppgaver selv om brukerhistoriene ikke var klart definerte?

Jeg tror faktisk her den sentrale idéen innenfor Scrum som går ut på at man oppretter brukerhistorier og arbeidsoppgaver FØR man går i gang med arbeidet virket litt hemmende på oss. I alle fall tenkte jeg ikke på det som en mulighet en gang. Kanskje vi her burde stilt oss litt friere fra prosjektmetodikken vår, for produktiviteten sin skyld?

Nå har etterpåklokskapen naturligvis begynt å falle på plass. Gitt den knappe tiden vi hadde igjen da vi begynte å utvikle multiplayer, burde vi heller bare fokusert på å utvikle en smartere AI, og heller arbeide mer med andre deler av MVP som heller ikke er inkludert (som en nedtelling på programmeringen av kort og muligheten for powerdown). Men det er ikke alltids enkelt å innse at man har tatt vann over hodet, og på det tidspunktet vi begynte på multiplayerdelen så det ikke slik ut. Når det er sagt tror vi ikke at det er mange dagene med fokusert jobbing som skal til for å ferdigstille en fungerende multiplayer.

Nå virker det som om vi er kjempemisfornøyd med alt vi har gjort, men det er langt i fra tilfellet. Sett bort i fra de litt uheldige omstendighetene rundt vår siste sprint, så har effektiviteten, arbeidsfordelingen og oversikten over prosjektet vært til å skryte av i de fleste perioder. Spesielt var det lurt å bruke project boardet i GitHub sammen med Issues og alle de praktiske funksjonalitetene som følger med der. Vi har opprettet tags på issues slik at vi ser hva issuet gjelder (kode, UI, bugfix), milestones for å se hva sprint oppgaven tilhører og brukt assignmentfunksjonen til å holde oversikt over hvem som gjør (eller skal gjøre) de konkrete oppgavene. Dette aspektet ved gruppeprosjektet er gruppen storfornøyd med, og det var ekstra praktisk etter skolen stengte og vi ikke snakket med hverandre så ofte lengre.

Vi er også veldig fornøyde med at vi valgte å fokusere på å først utvikle spillogikken på en grundig og robust måte, slik at all brettfunksjonalitet og annen spillfunksjonalitet var på plass tidlig i utviklingen (bortsett fra powerdown som krever et rundebasert spill for å gi mening.) Det var komfortabelt å begynne å implementere kortprogrammering og runder i spillet, når all underforliggende logikk virket som den skulle.

### Prosjektmetodikk

I startfasen av gruppeprosjektet bestemte vi oss for å ta utgangspunkt i prosjektmetodikken Scrum med vekt på oppretting av brukerhistorier i begynnelsen av en sprint, testdrevet utvikling og parprogrammering. Da forrige innlevering skulle inn ble vi enige om for fleksibilitetens skyld å åpne for å legge til arbeidsoppgaver til project boardet når som helst i sprinten, uten at de tilhørte en spesiell brukerhistorie. Vi bevegde oss dermed over i en metodikk som lignet Kanban, spesielt med tanke på at project boardet ble så sentralt for arbeidet vårt. Likevel ønsket vi å beholde den iterative måten å jobbe på som står sentralt i Scrum.

Planen var egentlig å fortsette med denne metodikken, da den synes å passe oss utmerket. Men på grunn av de spesielle hendelsene som skjedde rundt påsketider kombinert med ferie og brakkesyke, ble det aldri noen anledning for oss til å avslutte én sprint og starte på en ny. Etter å ha opprettet de originale brukerhistoriene for andre gang, fant vi også behovet for andre funksjonaliteter som ikke ble dekket, og dermed opprettet vi enda flere brukerhistorier. Til slutt innså vi at dette ble den siste sprinten, ettersom hele MVP ble dekket av brukerhistoriene som var opprettet (og begynt på) til da.

Så hvilken prosjektmetodikk bruker vi i praksis? Ettersom vi i denne sprinten la mye vekt på å opprette alle brukerhistoriene før vi gikk i gang med arbeidet, er jo det viktigste elementet fra Scrum på plass. Å la én sprint vare i en halvannen måned er litt drøyt selv i Scrum sin standard (hvor grensen er 4 uker), men med tanke på at tre av de ukene nærmest ikke gikk til noe arbeid, så føler vi at det er innafor Scrum sine grenser. Likevel har vi som nevnt opprettet nye brukerhistorier og arbeidsoppgaver etter vi trodde at alle var på plass; frihet i forhold til project boardet og oppretting av nye issues har vært sentralt for vår arbeidsprosess denne gangen også. Dermed føler vi at vi har en prosjektmetodikk som er en slags mellomting mellom Scrum og Kanban.

Testdrevet utvikling har vi alltid hatt blandede følelser om, men vi har alltid vært enige om å la det være en sentral del av vår utvikling. Det vil si at vi har forsøkt å drive med det der det lar seg gjøre. I denne sprinten har egentlig det meste av spillogikk vært på plass fra før av, og det har stort sett dreid seg om å få til runder, UI-kortprogrammering samt annen UI funksjonalitet og multiplayer til å funke. Dette er ting som er alt fra krevende til umulig å skrive tester til, så i denne omgang har ikke testdrevet utvikling vært en sentral del av metodikken vår.

Når det gjelder parprogrammering, har jo dette nærmest blitt umulig å få til pga. korona lockdown. Likevel forsøker vi ofte å kode sammen i Discord å snakke hyppig sammen om designvalg, for å oppnå den samme &quot;flere hoder tenker bedre enn ett&quot;-effekten.

### Forbedringspunkter

1. Vi kan bli flinkere til å ferdigstille brukerhistoriene med arbeidskrav kjapt etter avsluttet forrige sprint, slik at vi kommer oss hyppig i gang
2. Generelt sett jobbe litt jevnere med oppgavene, i stedet for å ta skippertak i dagene før sprinten er over

## Gruppedynamikk og kommunikasjon

Gruppedynamikken vår nå er så klart ikke den samme som vi hadde i begynnelsen. Litt ut i semesteret hadde vi en ganske god gruppedynamikk der vi klarte å samarbeide og kommunisere godt ved hjelp av hyppige møter og felles parprogrammering. Dette ble det en ganske brå stopp på når universitetet stengte ned, og vi har følt effektene det har hatt på gruppedynamikken vår. Hvertfall i begynnelsen var det vanskelig å samle alle til møter, og den generelle kommunikasjonen vår var ikke i nærheten av like god som den var i begynnelsen. Sakte men sikkert har vi vendt oss til den nye tilværelsen vår, vi har fått satt opp mer effektive måter å kommunisere på (Discord og Zoom) og vi har blitt bedre på å finne tider der vi alle kan være samlet. Nå opp mot siste innlevering er gruppedynamikken egentlig så god som vi kan forvente i den situasjonen vi er i. Vi er blitt flinke til å samle oss kjapt til møter og vi har flere møter for uken enn det vi har hatt før. Vi er blitt godt vant med hvordan de andre på teamet liker å jobbe og hvordan vi kan samarbeide mest effektivt. Dersom noen lurer på noe så er det lav terskel for å spørre om ting på discord eller messenger, og dersom noen gjør store endringer i kodebasen så forklarer de for resten av gruppa hvordan man skal bruke den nye implementasjonen. Alt i alt så vil vi si at gruppedynamikken vår er ganske solid, selv med korona krisen tatt i betraktning.

## Karantene og nedstenging

Nedstengingen av universitet påvirket gruppen på en negativ måte. Måten vi jobbet best på før nedstengingen var gjennom parprogrammering. Når dette ikke lenger var mulig måtte vi gå over til alternative arbeidsmetoder. Gruppemøtene har foregått over discord, og der har vi også samarbeidet. Nå når vi har måttet jobbe for oss selv, har det tatt lenger tid å løse problemer enn det det ville tatt hvis vi kunne parprogrammert. Vi har selvfølgelig brukt discord og skjermdeling for å samarbeide og hjelpe hverandre, men det har på lang grad ikke vært like effektivt som det å sitte sammen og jobbe.

Vi har også slitt med å finne arbeidslyst og motivasjon nå under nedstengingen. Selv om vi har hatt mer tid til overs nå enn det vi hadde før, synes vi det har vært vanskelig å sette oss ned og jobbe med prosjektet.

## Deloppgave 2: Krav

Siden forrige gang har vi prioritert spill logikken, grafikk og multiplayer.

Vi har implementert:

- Runder og faser
- Muligheten til å programmere roboten med kort
- Status som viser en oversikt over de andre spillerne
- Multiplayer (ikke helt i mål)

Vi anser fortsatt MVP som det absolutte minimum av funksjonalitet som vi trenger for at spillet kan fungere, MVP er da:

- Et Kart som inneholder alle spill-objektene vi trenger for å spille RoboRally. Kartet må altså inneholde:
  - Vegger
  - Laser
  - Flag
  - Hull
  - Tannhjul
  - Transportbånd
  - Skiftenøkkel
  - Roboter
- En overordnet spill-klasse som behandler spill-logikk og viser kartet grafisk til brukeren.
- En Robot som kan
  - Bevege seg
  - Miste liv
  - Dø
  - Gjenoppstå
  - Powerdown
- En spiller som kan:
  - Få utdelt programkort
  - Legge ned et program
  - Tape/vinne
- Brukergrensesnitt som lar brukeren:
  - Se spillbrettet grafisk
  - programmere roboten sin
  - velge powerdown
- Rammeverk for lokal multiplayer

**Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**

1. Når man endrer på skjermstørrelsen når kort deles ut vil de bli plassert litt rare steder. Har en funksjon som skulle kompensere for dette, men den ser ikke ut til å fungere som den skal. Tiden er nå litt knapp så vi har valgt å plassere fokus andre steder.
2. Multiplayer funker ikke, så hvis man prøver å kjøre spillet som en host, så krasjer spillet.
3. Multiplayer: hvis man signer up som client, prøver å koble seg til en feilaktig IP-adresse, får avslag, og så signer up som host, så krasjer appen.

## Deloppgave 3: Produktleveranse og kodekvalitet

## Manuelle tester

**Manuell test for å sjekke at oversikten over de andre spillerne fungerer:**

Test 1: Stiller meg opp med piltastene slik at jeg treffer en annen robot hvis jeg skyter med laseren. Skyter laseren på en robot, da skal det oppdateres på venstre side at roboten har fått ett damagetoken.

Test 2: Starter likt som test 1. Skyter roboten med laser 10 ganger. For hvert skudd skal roboten få ett damagetoken. Når roboten har fått ti damagetokens skal den miste ett lifetoken.

**Manuelle tester for programkort**

Test 1: Begynn med et nytt spill. Når spillet starter, trykk på det av kortene på &quot;hånden&quot;. Forventet resultat er at kortet beveger seg fra hånden til registeret.

Test 2: Begynn med et nytt spill. Utfør Test 1. Når du nå har et kort i registeret forsøk å trykke på det. Det er forventet at kortet skal flytte seg tilbake til sin originale plass på i &quot;hånden&quot;.

Test 3: Begynn med et nytt spill. Utfør Test 1, men denne gangen trykk på flere enn 1 kort. Forsøk nå å trykke på kortet som er lengst til venstre i registeret. Kortet som ble trykket på burde flytte seg tilbake til hånden. Kortene som var igjen i registeret burde flytte seg til venstre for å fylle &quot;tomrommet&quot;. Dette sørger for at programsheet invarianten er vedlikeholdt.

Test 4: Begynn med et nytt spill. Når spillet starter, velg 5 tilfeldige kort og &quot;plasser&quot; dem i registeret. Når registeret er fullt starter testen. Dersom du nå trykker på et av kortene som ikke enda er i registeret. Det forventede resultatet er at kortet ikke rører på seg, og alt er som før du trykket.

Test 5: Begynn med et nytt spill. Når spillet starter, sørg for at spilleren har flere enn 5 damagetokens. Dette kan gjøres ved å trykke Z for å aktivere debug modus, deretter bruke piltastene til å plassere spilleren foran en laser, for så å trykke L gjentatte ganger til ønsket mengde damagetoken er oppnådd. Når du har mer enn 5 damagetokes, start en runde med tilfeldige kort fra hånden. (Sørg for at spilleren ikke dør ila runden). Når runden nå er ferdig, burde det være noen låste kort i registeret. Disse kortene er det forventet at du ikke skal kunne fjerne.

Test 6: Begynn med et nytt spill. Utfør test 5. Når runden er ferdig og de låste kortene fortsatt er til stede. Trykk på et av de låste kortene, det er forventet at kortet ikke skal bevege seg.

**Manuelle tester for multiplayer**

Test 1: Start et nytt spill, på menyen velg multiplayer istedenfor singleplayer. Skriv et navn og velg deretter host. Du bør komme inn i en skjerm hvor det står du venter på spillere. På en annen maskin, gjør tilsvarende, men trykk join istedenfor. Skriv inn den lokale IPen, og trykk connect. Det er forventet at begge spillernes navn skal vises på skjermen.

## Brukerhistorier

## Iterasjon 4: 31.03.20 (de facto start 17.04.20)

### Påminnelse: spillets tilstander

- Tilstand 1: Deal programmkort
- Tilstand 2: Legge ned programmkort (skjult for andre)
- Tilstand 3: Spiller kan velge powerdown
- Tilstand 4: Repeteres 5 ganger
  - Alle viser programkort samtidig
  - Robotene beveger seg
  - Conveyors, gears og pushers aktiveres
  - Lasere skyter
  - Registrer checkpoints
- Tilstand 5: Cleanup:
  - repairs - skirftenøkler
  - discard alle ubrukte playercards

## Høyt prioritert

Som spiller ønsker jeg et fasebasert spill hvor hver runde består av fem faser, slik at brettinteraksjon styres av spillets faser i henhold til reglene

- Arbeidsoppgaver
  - Implementere RoundHandler og funksjonene klassen vil trenge for å styre gangen i en fase
  - Integrere fasefunksjonene fra RoundHandler i selve spillet
- Akseptansekrav

Som spiller ønsker jeg å ha egne områder på skjermen til mitt program sheet (med 5 registers) og inventory (med tilgjengelige kort), slik at jeg

1. Kan programmere roboten min interaktivt
2. Legge ned et ferdig program
3. Har en oversikt over program sheetet til enhver tid

- Arbeidsoppgaver
  - Program Sheet klasse som skal simulere spillerens registers. Må kunne låse registers og andre spillrelevante funksjoner
  - Program Sheet UI klasse/metode som tar et Program Sheet og rendrer det på et eget område på skjermen
  - Inventory UI klasse/metode som tar en liste med kort (spillerens inventory) og rendrer det på et eget område på skjermen
  - Legge til listeners på kort, slik at spiller kan flytte kort frem og tilbake mellom Program Sheet og Inventory når de klikkes på
  - Ha en &quot;Confirm Program Sheet&quot;-knapp som &quot;locker inn&quot; det valgte Program Sheetet
- Akseptansekrav (antar at spillet nå venter på at jeg skal lage program)
  - Hvis &quot;programmeringsfasen&quot; akkurat har begynt, så er mitt Program Sheet tomt og min Inventory full
  - Hvis Program Sheetet har ledig plass og jeg trykker på et kort i Inventory, så flyttes kortet over til den første ledige plassen i Program Sheet (fra venstre)
  - Hvis Program Sheetet ikke har ledig plass og jeg trykker på et kort i Inventory, så er tilstanden uforandret.
  - Hvis jeg trykker på kortet mest til høyre i Program Sheetet, så flyttes det over i Inventory.
  - Hvis jeg trykker et kort som ikke er mest til høyre i Program Sheetet, så flyttes det over i Inventory. Alle kort i Program Sheet til høyre for dette kortet flyttes et hakk til venstre (de tetter igjen hullet).
  - Hvis Program Sheet inneholder mindre enn 5 kort, så har jeg ikke mulighet til å trykke &quot;Confirm Program Sheet&quot;
  - Hvis Program Sheet inneholder 5 kort, har jeg mulighet til å trykke &quot;Confirm Program Sheet&quot;.
  - Hvis &quot;Confirm Program Sheet&quot; trykkes, så avsluttes programmeringsfasen, og Program Sheetet mitt er låst inntil videre.

## Lavere prioritet

Som spiller ønsker jeg å spille multiplayer over LAN sammen med andre maskiner som har spillet installert, slik at jeg både kan spille med mennesker i tillegg til (simple) AIs

- Arbeidsoppgaver
  - Grunnleggende klient- og serverfunksjonalitet som støtter sending og mottak av spillerlister og program sheets
  - Startmenyfunksjonalitet: spilleren kan registrere seg som host/server: viser IP og foreløpig påkoblede spillere (party)
  - Startmenyfunksjonalitet: hosten kan starte spillet med de foreløpig registrerte spillerene i partyet
  - Startmenyfunksjonalitet: hosten kan legge til AIs i partyet
  - Startmenyfunksjonalitet: spilleren kan koble seg til en host/server ved å skrive inn server IP og velge et unikt navn
  - Integrere multiplayerfunksjonalitet inn i spillogikken: programmeringsfasen begynner på request av servertråden
  - La serveren bestemme tidspunkt for når programmeringen skal starte og slutte
- Akseptansekrav
  - Som spiller vil jeg ha muligheten til å hoste et game.
  - Hvis jeg bestemmer meg for å hoste et game (trykker &quot;Host game&quot;), så får jeg opp min egen IP adresse.
  - Hvis jeg bestemmer meg for å hoste et game (trykker &quot;Host game&quot;), så får jeg opp navnet mitt og navnet på alle andre tilkoblede spillere.
  - Hvis jeg bestemmer meg for å joine et game (trykker &quot;Join game&quot;) så får jeg skrive inn IP adresse til lobbyen, og deretter se mitt og de andre tilkoblede spilleres navn.

Som spiller ønsker jeg å ha en grafisk oversikt over min egen status (damage tokens, life tokens), slik at jeg slipper å ha denne informasjonen i hodet

- Arbeidsoppgaver
  - Grafisk område til å vise spillerens life tokens i form av 3 hjerter (rød for life token, svart for ikke)
  - Grafisk område til å vise spillerens damage tokens i form av 10 fargekodete trekanter
  - Render-metode: tar inn antall life tokens, og rendrer tilsvarende mengde hjerter
  - Render-metode: tar inn antall damage tokens, og rendrer tilsvarende mengde trekanter
- Akseptansekrav
  - Hvis jeg mister eller får lifetokens vil jeg se det grafisk
  - Hvis jeg mister eller får damagetokens vil jeg se det grafisk

Som spiller ønsker jeg å ha en grafisk oversikt over mine medspilleres status (damage tokens, life tokens, program sheet), slik at jeg enkelt kan verifisere at ingen jukser

- Arbeidsoppgaver
  - Render-metode: tar inn en medspiller, og rendrer spillerens Navn/ID, registers/kort, antall life tokens, antall damage tokens på et eget område øverst på skjermen
  - Rendrer statusen til alle medspillere som enda er med i spillet i sanntid
- Akseptansekrav
  - Hvis en annen spiller mister eller får lifetokens vil jeg se det grafisk
  - Hvis en annen spiller mister eller får damagetokens vil jeg se det grafisk

Som spiller ønsker jeg å ha et eget område på skjermen dedikert til spillopplysninger som oppdateres i sanntid, slik at jeg

1. blir oppdatert på de viktigste hendelsene i spillet
2. blir opplyst om nåværende spilltilstand
3. blir fortalt hva jeg må gjøre i dette stadiet av spillet

- Arbeidsoppgaver
  - Grafisk område på høyre side med en guide-robot som skal &quot;snakke&quot;
  - Render-metode: tar inn en streng, og rendrer en snakkeboble med strengen over robotens &quot;hode&quot;. Må kunne støtte flere strenger samtidig
  - Klasse som styrer hvilke beskjeder som rendres. Metodikk for rendering av standard beskjeder basert på spillets og spillers tilstand
- Akseptansekrav
  - Hvis en eller flere av følgende ting skjer vil jeg se det i en logg på skjermen:
    - En spiller blir truffet av laser.
    - En eller flere spillere blir dyttet.
    - En spiller dør.
    - En spiller treffer et flag.
    - En spiller er på rullebånd
    - En spiller er på tannhjul
    - En spiller er på &quot;repair site&quot;
    - En spiller blir dyttet av &quot;pusher&quot;

Som bruker ønsker jeg å ha en velstrukturert README slik at jeg kan finne ut hvordan jeg kan laste ned og starte spillet uten problemer

- Arbeidsoppgaver
  - Restrukturere README
  - Skrive inn hvordan prosjektet bygges, testes og installeres fra commandline.

## Brukerhistorier som krever et fasebasert spill

Som spiller ønsker jeg at alle roboter legger ned et program _i starten av en runde_, slik at robotbevegelse kan skje i takt med spillets faser

- Arbeidsoppgaver
  - Knytte funksjonalitet for å legge ned program sammen med det fasebaserte spillet, slik at dette skjer i begynnelsen av hver runde
  - Lage en klasse med alle program sheets som gir en liste av spiller-id og deres tilhørende kort/bevegelse basert på kort-prioritet i korrekt rekkefølge (bruk tree-map!)
  - Integrere funksjonalitet for at alle spillere blir flyttet i henhold til sitt programsheet i korrekt rekkefølge
- Akseptansekrav
  - Hvis jeg har valgt kort må jeg trykke &quot;confirm&quot;
  - Hvis jeg ikke har valgt kort og trykket &quot;confirm&quot; flytter ingen roboter seg.

Som spiller ønsker jeg å ha muligheten til å velge powerdown etter programmeringen er over, slik at jeg kan fylle opp livet til roboten min i neste runde.

- Arbeidsoppgaver:
  - Implementere logikken bak powerdown (Spilleren slutter å bevege seg, fyller opp liv)
  - Implementere en powerdown-knapp på skjermen som blir tilgjengelig for spilleren på korrekt tidspunkt (Tilstand 3)
- Akseptansekrav
  - Hvis spilleren klikker på powerdown, skal ikke den spilleren gjøre noen bevegelser i løpet av runden.
