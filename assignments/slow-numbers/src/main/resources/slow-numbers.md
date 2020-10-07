# Slow numbers

1. Maak een taak die de cijfers 0 t/m 9 naar de console wegschrijft.
1. De taak moet 1 seconde wachten alvorens het volgende nummer te printen.
1. De taak moet op een aparte thread draaien.
1. De main-thread start de taak en wacht vervolgens 3 seconden voordat het programma afsluit.
1. Bij het afsluiten moet de applicatie de lopende taak verzoeken om het werk af te ronden.
1. Na het verzenden van dit verzoek wacht de applicatie wacht maximaal 1 seconde op het evt. voltooien van de taak.
1. De lopende taak moet na het ontvangen van een afsluitverzoek direct de uitvoer stoppen.
