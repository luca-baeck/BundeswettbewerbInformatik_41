
zitate = []

def readFile(filename):
    f = open(filename, "r")
    return f.read()
def getDistance(l):
    j = 1
    if((l+1) > len(split_nachricht)-1):
        return 0
    for k in range(l+1, len(split_nachricht) - 1):
        if (split_nachricht[k] != "_"):
            return j
        else:
            j = j + 1
    return j

content = readFile("Alice_im_Wunderland.txt")
content = content.replace("\n", " ")

content = content.replace("--", "")
content = content.replace("»", "")
content = content.replace("«", "")
content = content.replace("(", "")
content = content.replace(")", "")
content = content.replace("[", "")
content = content.replace("]", "")

split_content = content.split(" ")
split_content[:] = [x for x in split_content if x]

nachricht = readFile("stoerung1.txt")
split_nachricht = nachricht.split(" ")

# Es wird bei der folgenden Verarbeitung davon ausgegangen, dass die übermittelte Nachricht im Format xxx _ _ yyy _ zzz ist.
for i in range(0, len(split_content)-1):
    if(split_content[i] == split_nachricht[0]):
        position = 0

        zitat = split_nachricht[position]

        current_distance = getDistance(position)
        check = True
        while(current_distance != 0):
            position = position + current_distance

            for j in range(i+1, i+position):
                zitat = zitat + split_content[j]
            print(current_distance)

            print("compare: " + split_nachricht[position] + " mit " + split_content[i+position])
            if(split_content[i+position] == split_nachricht[position]):
                print(position)
                current_distance = getDistance(position)
                print("found one")
            else:
                current_distance = 0
                check = False

        if(check):
            zitate.append(zitat)
print(zitate)

