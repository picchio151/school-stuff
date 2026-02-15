# Music UDP App

Applicazione client-server realizzata in Java che utilizza il protocollo **UDP (UDA)**.


## Funzionalità

Il server mette a disposizione due servizi:

- **Richiesta di una canzone**
  - formato: `SONG:nomefile.mp3`
- **Richiesta di un mashup**
  - formato: `MASHUP:canzone1.mp3,canzone2.mp3`

Il mashup è simulato tramite una risposta testuale.

---

## Struttura del progetto

- `MusicClient.java`  
  Client UDP che invia una richiesta al server e riceve la risposta.

- `MusicServerIterative.java`  
  Server UDP iterativo e single-thread che elabora le richieste dei client.

---
