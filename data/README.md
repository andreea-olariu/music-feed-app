# data

Scopul acestor scripturi de Python este obtinerea datelor necesare pentru nevoile aplicatiei pe care am proiectat-o.

Plecand de la CSV-ul de la laborator cu albume faimoase, am extras din CSV doar datele de care am avut nevoie si, folosind libraria spotipy din Python care lucreaza cu Web API-ul Spotify, am extras si melodiile de pe acele albume.

in **extract_from_csv** am extras din CSV-ul de la laborator doar anumite zone de care am avut nevoie 
in **extract_with_spotipy** am parcurs acel CSV, pentru fiecare album am facut o cerere de a obtine melodiile din album si am scris toate aceste informatii in **cleaned_tracks.csv**. Structura acestui document este track, album, year, artist, genre.


