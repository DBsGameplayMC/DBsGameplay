# DBsGameplay Survival
> Konzept für den Survival-Modus

<!-- ------------- -->
## Inhaltsverzeichnis

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [DBsGameplay Survival](#dbsgameplay-survival)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [2 Grundkonzept](#2-grundkonzept)
    - [2.1 Zonen](#21-zonen)
    - [2.2 PvP](#22-pvp)
    - [2.3 Clans](#23-clans)
    - [2.4 Region](#24-region)
  - [3 Befehle](#3-befehle)
    - [3.1 /Fill](#31-fill)
    - [3.1 /Auktion](#31-auktion)
    - [3.2 /Bieten](#32-bieten)
    - [3.3 /Vorschau](#33-vorschau)
    - [3.4 /Mülltonne](#34-mülltonne)
    - [3.5 /Confirm](#35-confirm)
    - [3.6 /Cancel](#36-cancel)

<!-- /code_chunk_output -->

---

<!-- ------------- -->
## 2 Grundkonzept
<!-- ------------- -->
### 2.1 Zonen

**Zone 1: Safe zone:**

In der `Safe zone` ist jeder Spieler sicher. Hier ist das Platzieren und Abbauen von Blöcken deaktivert, PvP verboten und enthält keine Fallen. In der `Safe zone` steht das Spawngebäude, welches nützliche Alltagsblöcke wie z.B. Crafting Tables, Ender Chests Anvils, etc. 

In das Spawngebäude sind einsparungen für Hologramme einzubauen, die den Spielern entweder Informationen liefern oder Werbung für unsere Seiten (Social Media, Discord, etc.) machen.  

Hier stehen

**Zone 2: Gladiator ring:**

**Zone 3: Outlaw:**

<!-- ------------- -->
### 2.2 PvP
<!-- ------------- -->
### 2.3 Clans
<!-- ------------- -->
### 2.4 Region
<!-- ------------- -->

---

## 3 Befehle
> Alle Befehle und argumente sind sowohl auf Deutsch als auch auf Englisch verfügbar.

### 3.1 /Fill
> Füllt alle Glasflaschen im Spieler-Inventar mit Wasser.

**Syntax:**
- `/fill`
    - Berechtigung: `dbsgameplay.survival.regularplayer.fill`

### 3.1 /Auktion
> Ermöglicht es Spielern, Items zur Auktion anzubieten, darauf zu bieten und eine Vorschau des zur Auktion steht.

**Syntax:** 
- `/auktion [Mindestgebot]` Erstellt eine Auktion mit dem angegebenen Mindestgebot und dem Item in der Main-Hand des Spielers.
    - Berechtigung: `dbsgameplay.survival.regularplayer.auction`

 

- `/auktion [on|off]` Schaltet das Auktions-Feature z.B. für ein Event ein- oder aus.
    - Berechtigung: `dbsgameplay.survival.mod.auction.toggle`

- `/auktion abort [Grund]` Bricht die aktuelle Auktion ab und gibt dem ursprünglichem Spieler das Item zurück, falls es z.B. unangemessen benannt wurde.
    - Berechtigung: `dbsgameplay.survival.team.auction.abort`

### 3.2 /Bieten
> Bietet Geld auf eine aktive Auktion.

**Syntax:** 
- `/bieten [Gebot]`
  - Berechtigung: `dbsgameplay.survival.player.fill`

### 3.3 /Vorschau
> Zeigt das zur Auktion stehende Item in einer GUI.

**Syntax:**
- `/vorschau` 

### 3.4 /Mülltonne
> Gibt dem Spieler eine Mülltonne.

**Syntax:**
- `/trashcan`
  - Berechtigung: `dbsgameplay.survival.team.trashcan`

### 3.5 /Confirm
> Universeller Bestätigungs-Befehl für Befehle wie z.B. [3.2 /Bieten](#32-bieten)

**Syntax:**
- `/confirm` Bestätigt einen zuvor ausgeführten Befehl

### 3.6 /Cancel
> Universeller Abbrech-Befehl für Befehle wie z.B. [3.2 /Bieten](#32-bieten)

