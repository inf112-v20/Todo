# Møtereferat 20-03-2020

Alle gruppemedlemmene møtte opp på discord.

Vi jobbet videre med brukerhistoriene fra sprint 2 og 3. Noen av oss slett med rare feilmeldinger, da var det greit å gå gjennom det i plenum, slik at alle nå vet hvorfor det skjedde og hvordan vi unngår det fremover. Vi fant ut at Tiled har y-aksen i motsatt retning som resten av koden vår, og dette var årsaken til en rekke bugs. Vi ble enige om la akseretningene være for nå, men det er ikke utenkelig at vi endrer koden vår til å følge Tiled sitt koordinatsystem i fremtiden. Vi diskuterte hvordan vi skal refraktorere movementHandler, movementHandler er nemlig ikke kun ansvarlig for bevegelse. Men også mye annen spill logikk som kan plasseres i evt andre klasser. Vi tar et møte til iløpet av helgen, hvor vi diskuterer hva som er essensielt å gjøre og hvordan vi distribuerer oppgavene for neste uke.



**Dette har vi gjort siden forrige møte (fredag 13.03.2020)**

**Sigve** har måtte jobbe med de andre fagene sine og har dermed utsatt å jobbe med prosjektet.

**Pål** har som planlagt arbeidet med å implementere en CardFactory klasse. Under implementasjonen av denne klassen oppdaget jeg at jeg måtte refaktorere &quot;deck&quot;/&quot;kortstokk&quot; klassen. Tidligere tenkte vi at hver spiller skulle ha en egen kortstokk. Men vi kom i dag fram til at dette var feil, og at det skulle være en delt kortstokk mellom alle spillerne. Dette er fordi spillere ikke skal kunne trekke kort med lik prioritet blant annet. Det jeg for øyeblikket arbeider med er å implementere en kortstokk som alle spillere trekker fra. Denne kortstokken skal inneholde en aktiv-bunke og en &quot;søppel&quot;-bunke. Kort spilleren ikke velger å spille vil bli lagt i søppel-bunken. Her er det mange egdekases, og det krever mye testing.

**Øyvind** jobbet med å implementere gears på brettet. Har ikke hatt mye tid til overs til å jobbe med dette faget pga andre innleveringer.

**Eskil** har implementert laserfunksjon på brettet, slik at både spillere og &quot;laservegger&quot; skyter lasere når man trykker på &quot;L&quot;. LaserHandler-klassen samler også inn spillere som treffes av lasere, samt hvor mange stråler de blir truffet av. Denne informasjonen gjøres ingenting med foreløpig, men blir relevant når HP implementeres.

**Alvar** har som planlagt arbeidet med å implementere ConveyorTiles og funksjon for samlebånd.

Til nå så fungerer vanlige samlebånd slik de skal, og vanlige conveyorTiles har unittester. Har i tillegg fikset et par mindre bugs som dukket opp i løpet av uken.
