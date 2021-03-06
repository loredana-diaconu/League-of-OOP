Diaconu Maria-Loredana, 325 CA

Clase utilizate:

I. INPUT si INPUTLOADER: asemanatoare cu cele din scheletul de la tema1, se
   ocupa cu citirea datelor din fisier, punerea lor sub o forma mai prietenoasa
   si scrierea in fisierul de output.
II. MAIN: Se ocupa cu desfasurarea jocului in sine, mai precis ordinea eveni-
    mentelor:
    * Se obtin datele din fisierul de intrare.
    * Se pozitioneaza jucatorii pe harta.
    * Pentru fiecare jucator, se verifica daca trebuie sa incaseze vreun damage
      din turele trecute, apoi se verifica daca se poate misca si, in caz ca
      da, se muta jucatorul.
    * Se verifica ce lupte vor avea loc pe harta, operatie atribuita hartii.
    * Cand se termina rundele, se afiseaza rezultatul.
III. MAP si TERRAIN: Map reprezinta intreaga harta, iar Terrain, o parcela din
     harta, de un anumit tip (land, volcanic, desert, woods). Pe harta se pot
     adauga sau sterge jucatori de pe o anumita pozitie, se detecteaza si se
     desfasoara luptele. Terrain retine intr-un arrayList cati si ce jucatori
     se afla pe parcela descrisa.
IV. PLAYER: implementeaza interfata Attackable si descrie un jucator (ce poate
    fi atacat). Acesta are mecanism de level up, se poate misca, poate muri,
    poate fi oprit, retine o pozitie, damageul overtime si rundele in care nu
    se poate misca. Poate sa lupte cu un alt jucator. Jucatorii sunt de mai
    multe tipuri (subclasele Knight, Rogue, Pyromancer si Wizard), ce au fie-
    care cate doua abilitati, modificatori de tip si un teren pe care sunt mai
    puternici.
v. Clasele ce implementeaza ABILITY: descriu abilitatile jucatorilor. Prin me-
   canismul de Double Dispatch, mai exact utilizand Visitor Pattern, abilitati-
   le pot vizita jucatorii (aplica pe fiecare damageul corespunzator). Acestea
   sunt si ele de mai multe tipuri. Abilitatile pot da damage sau stun pe o pe-
   rioada mai prelungita. Damageul este retinut pentru fiecare jucator intr-un
   arrayList (ca intr-o coada) si sters dupa ce este aplicat. Incapacitatea
   este retinuta sub forma unui numar de runde in care jucatorul nu se poate
   misca, ce scade pe parcurs.