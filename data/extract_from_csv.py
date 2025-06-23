import csv
def clean_data():
    f_read = open('./rym_top_5000_all_time.csv', 'r')
    f_write = open('./cleaned_albums.csv', 'w')

    writer = csv.writer(f_write)
    reader = csv.reader(f_read, delimiter=',')
    new_row = []
    for row in reader:
        new_row.append(row[1])
        new_row.append(row[3][-4:])
        index = row[2].find("/")
        if index != -1:
            new_row.append(row[2][0:index])
        else:
            new_row.append(row[2])
        index = row[4].find(",")
        if index != -1:
            new_row.append(row[4][0:index])
        else:
            new_row.append(row[4])
        writer.writerow(new_row)
        new_row = []


clean_data()

