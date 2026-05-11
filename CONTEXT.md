# CONTEXT.md — quizer-core-lib

> **Contexte projet** : [CONTEXT.md racine](../CONTEXT.md)
> **Documentation locale** : [documentation/README.md](./documentation/README.md) — notes et décisions propres à ce module
> **Documentation technique centrale** : [documents-qmaker-private/](../documents-qmaker-private/)

---

## Identité du module

- **Name:** `quizer-core-lib`
- **Package:** `com.qmaker.quizzer.core`
- **Type:** Android Library (JAR), publié comme `com.qmaker:quizzer-core:1.0.0`
- **Role:** Bibliothèque de domaine **purement Java** pour la gestion des modes quiz chronométrés. Étend `qcmcore` en ajoutant les concepts de quiz avec ambiance, timing, et séquençage.
- **Depends on:** `:qcmcore`

## Build

```bash
./gradlew :quizer-core-lib:assembleRelease
./gradlew :quizer-core-lib:makeJar
```

- compileSdk 26, minSdk 19, Java (source compatibility par défaut)
- Se compile en JAR (via `release-jar.gradle`)

## Structure du code

| Package | Rôle |
|---------|------|
| `entities/` | `Quiz`, `Ambiance` |
| `interfaces/` | `QuizProvider` |
| `engines/` | `Quizzer` — moteur de quiz |
| `utils/` | `Toolkits`, `ArrayListQuizProvider` |

### Classes clés

| Classe | Rôle |
|--------|------|
| `Quiz` | Séance de quiz chronométré — étend la notion d'`Exercise` (qcmcore) avec gestion du temps par question et modes de scoring |
| `Ambiance` | Configuration sonore/visuelle d'un quiz (musique d'ambiance, effets) |
| `Quizzer` | Moteur de quiz : séquençage des questions, contrôle du timer, transitions automatiques |
| `QuizProvider` | Interface : fournit le `Quiz` courant à un composant |
| `ArrayListQuizProvider` | Implémentation `QuizProvider` basée sur une `ArrayList` |

### Relation avec qcmexecice

`quizer-core-lib` fournit les modèles de domaine que `qcmexecice` utilise pour implémenter le mode quiz (`QuizActivity`). `Quizzer` est l'équivalent domaine de ce que `CountDownTimerPresenter` gère au niveau UI dans `qcmexecice`.

---

## Mission de ce module

`quizer-core-lib` ajoute à `qcmcore` les modèles et aides spécifiques au mode quiz, notamment `Quiz`, `Ambiance`, `Quizzer` et les providers associés. C'est un module de domaine léger, sans UI.

---

## Positionnement dans l'écosystème

- Dépend uniquement de `:qcmcore`
- Est surtout consommé par `qcmexecice` via `qcmmakercore`
- Son pendant Flutter est `../flutter/qcmmaker/quizercore`

---

## Règles de modification

- Garder ici des entités et helpers de quiz **génériques, sans logique de présentation Android**
- Toute évolution doit rester cohérente avec `qcmcore` et avec le port Flutter `quizercore`
- Ne pas changer silencieusement la structure sérialisée des modèles quiz partagés avec les runners ou les expériences de lecture

---

## Validation minimale

```bash
./gradlew :quizer-core-lib:testDebugUnitTest
```

Si `Quiz` ou `Ambiance` change, revisiter aussi `../qcmexecice` et `../flutter/qcmmaker/quizercore`.

---

## Convention de mise à jour de ce fichier

Mettre à jour quand : contrainte non évidente découverte, nouvelle classe, règle nécessaire en pratique, décision produit.
Ne pas mettre à jour pour : état d'avancement d'une tâche, ce qui est lisible dans le code ou `git log`.
Protocole de verrou : vérifier `CONTEXT.md.lock`, créer si absent, modifier, supprimer. Utiliser `CONTEXT.md.pending` si lock actif.

---

## Journal des mises à jour

<!-- Ajouter en tête de liste, ne jamais modifier les entrées existantes -->
- [2026-04-22] [Claude] Création initiale — fusion de quizer-core-lib/CLAUDE.md + AGENTS.md
