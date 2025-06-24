import spotipy
from spotipy.oauth2 import SpotifyOAuth
import cred
import csv

scope = "user-read-recently-played"

sp = spotipy.Spotify(auth_manager=SpotifyOAuth(client_id=cred.client_ID, client_secret=cred.client_SECRET,
                                               redirect_uri=cred.redirect_url, scope=scope))


def write_in_csv():
    f_read = open('./cleaned_albums.csv', 'r')
    f_write = open('./cleaned_tracks.csv', 'w')

    writer = csv.writer(f_write)
    reader = csv.reader(f_read, delimiter=',')
    new_row = ['track', 'album', 'year', 'artist', 'genre']
    writer.writerow(new_row)

    new_row = []

    line_count = 0



    for row in reader:
        if line_count == 0:
            line_count += 1
            continue

        album = extract_info_for_album(row[0])
        tracks = get_tracks_for_album(album['id'])
        for track in tracks:
            track_name = track['name']
            album = row[0]
            year = row[1]
            artist = row[2]
            genre = row[3]

            new_row.append(track_name)
            new_row.append(album)
            new_row.append(year)
            new_row.append(artist)
            new_row.append(genre)

            writer.writerow(new_row)


            new_row = []

        print(line_count)
        line_count += 1



def extract_albums():
    f = open('./cleaned_albums.csv', 'r')
    reader = csv.reader(f, delimiter=',')
    line_count = 0
    albums = set()

    for row in reader:
        if line_count == 0:
            line_count += 1
            continue

        albums.add(row[0])

    return albums





def get_tracks_for_album(album_id):
    results = sp.album_tracks(album_id)

    tracks = results['items']
    while results['next']:
        results = sp.next(results)
        tracks.extend(results['items'])

    return tracks




def extract_info_for_album(album_name):
    results = sp.search(q='album: ' + album_name, type='album')
    items = results['albums']['items']
    if len(items) > 0:
        album = items[0]
        return album



write_in_csv()