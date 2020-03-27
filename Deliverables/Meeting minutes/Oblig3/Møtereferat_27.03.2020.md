# Møtereferat 27-03-2020

Alle gruppemedlemmene møte opp på discord.

**Dette har vi blitt enige om**

Vi har blitt enige om å samle alle former for bevegelse, også de som forårsakes av samlebånd og pushers, i movementHandler-klassen. Tanken har alltid vært at denne klassen skal ha hovedansvaret for all bevegelse, og bare det, i tråd med SRP. Slik situasjonen er nå er movementHandler også input processoren, men dette skal endre seg. Vi er enige om å prøve å få opp et panel som er input processor, hvor spillere velger kort osv.

Vi har en bug om at lasere ikke fjernes visuelt når en spiller dreper en annen spiller ved laserskudd. Vi har valgt å ikke gjøre noe med denne buggen, siden den kommer av at vi bytter på input processor når spawn collision skjer. Men ettersom vi snart skal flytte hele input processor logikken, er det ikke vits å gjøre noe med foreløpig.

Vi har også blitt enige om hvem skal skal gjøre hva nå i de siste timene før oblig 3 skal leveres.

**Dette har vi gjort siden forrige møte (torsdag 26.03.2020)**

**Eskil** har skrevet på den obligatoriske oppgaven.

Siden forrige møte var i går, har ikke resten gjort noe siden den gang.
