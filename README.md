# Blockchain minimalista V1.0

## Descrizione

Prototipo educativo di Blockchain sviluppato in Java.

Il progetto dimostra:

- Concatenamento dei blocchi tramite hash
- Algoritmo crittografico SHA-256
- Meccanismo di consenso Proof of Work (PoW)
- Verifica dell'integrità della catena

## Obiettivi Didattici

- Comprendere il concetto di immutabilità
- Comprendere l'importanza della funzione hash
- Simulare lo sforzo computazionale del mining
- Verificare la sicurezza contro la manomissione

## Struttura del Blocco

Ogni blocco contiene:

- Hash corrente
- Hash precedente
- Data (payload)
- Timestamp
- Nonce

Hash calcolato come:
inputHash = previousHash + timeStamp + nonce + data

## Proof of Work

Un blocco è valido solo se il suo hash inizia con un numero di zeri
pari alla variabile *difficulty*.

Esempio:
difficulty = 4
hash valido = 0000ab34f98c...

## Validazione

La blockchain è valida se:

1. Ogni hash è corretto rispetto ai dati
2. Ogni previousHash corrisponde all'hash del blocco precedente

## Esecuzione

Compilare:
javac *.java

Eseguire:
java Main

## Requisiti

- Java 8 o superiore
- Nessuna libreria esterna
- Persistenza in memoria (ArrayList)

## Finalità

Questo progetto è puramente educativo e non deve essere utilizzato
in ambienti di produzione.
