# Smallest number in array

## Deel 1

We gaan een klasse schrijven waarmee we het kleinste getal in een array gaan bepalen. 

1. Maak een klasse `FindMinimumAction` met een instantievariabele `int[] data` en de volgende methode:
   * `private int computeMinimum()`;
2. Geef `computeMinimum()` een werkende (synchrone) implementatie en laat deze het gevonden minimum ook naar `System.out` wegschrijven.
3. Laat je testmethode naar `System.out` schrijven hoe lang de berekening heeft geduurd.
4. Laat de klasse `FindMinimumAction` afstammen van `RecursiveAction` en implementeer de methode `compute()`. Gebruik bij een array van 5 of minder elementen de synchrone methode `computeMinimum()`; in andere gevallen verdeel je de array in twee delen die je via het Fork/Join-framework verwerkt.
5. Kopieer de klasse `FindMinimumAction` en noem deze `FindMinimumTask`.  Laat deze afstammen van `RecursiveTask`. De `compute()`-methode moet nu het minimum gaan teruggeven in plaats van wegschrijven naar `System.out`.
6. Is de Fork/Join-oplossing sneller dan de synchrone oplossing bij 20 arrayelementen? Zo nee, zoek uit bij hoeveel elementen Fork/Join sneller is en maak een testmethode om dat te demonstreren.
7. Zou je dit probleem ook met (alleen) Streams kunnen oplossen? Probeer dit uit. 
8. Voer de testset van opgave 6. uit op je Streams-oplossing. Welke oplossing is sneller?
9. Maak je Streams parallel en voer de testset nogmaals uit. Welke oplossing is sneller?
