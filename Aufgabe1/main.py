import re

buch = "Alice_im_Wunderland.txt"
stoerung = "stoerung6.txt"

def readFile(filename):
    f = open(filename, "r", encoding='utf-8')
    return f.read()
def createRegex(nachricht):
    regex = nachricht.replace(" ", "\s")
    regex = regex.replace(".", "\.")
    regex = regex.replace("_", "[^\s]+")
    return regex


content = readFile(buch)
content = content.replace("\n", " ")
content = content.replace("  ", " ")

nachricht = readFile(stoerung)
print(nachricht)

regex = createRegex(nachricht)

pattern = re.compile(regex, re.IGNORECASE)
matches = pattern.finditer(content)


results = [match.group(0) for match in matches]

if(results):
    print("Folgende Zitate wurden gefunden: ")
    for zitat in results:
        print(zitat)

else:
    print("Der Lückentext passt zu keinem Zitat.")