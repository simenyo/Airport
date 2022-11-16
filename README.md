# Airport

I src/ mappen ligger tre javafiler som simulerer landinger og avganger fra en flyplass ved å bruke to køer for hver.
Brukeren setter antall "time-steps". I en tidsenhet kan et fly enten lande eller et fly ta av. Begge deler kan ikke skje likt.
Landinger og avganger skjer ved tilfeldige tidspunkt, er rullebanen opptatt må et fly stille seg bakerst i køen av fly som venter å ta.
Fly som skal lande blir prioritert, så et fly kan kun ta av hvis landingskøen er tom. Begge køene er begrenset til 10 plasser.
Programmet samler data mens det kjører som skrives ut til konsoll når simuleringen er ferdig.

I Conditional_Variables ligger en fil skrevet i C som tar for seg pthreads og synkroniseringen av de med conditional variables.





