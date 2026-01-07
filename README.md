# Programa Benestar URV

## Descripció
Aquest projecte implementa el **Programa Benestar URV**, desenvolupat com a tercera pràctica de l’assignatura de **Programació (curs 2025–2026)**.

L’aplicació permet gestionar:
- Activitats de benestar (d’un dia, periòdiques i en línia)
- Usuaris de la comunitat universitària (PDI, PTGAS i estudiants)
- Inscripcions, llistes d’espera i valoracions

## Estructura del projecte
```text
.
├── src/            # Codi font Java
│ ├── aplicacio     # Aplicació
│ ├── interficie    # Interfície gràfica (Swing)
│ ├── dades         # Classes de domini i persistència
│ └── proves        # Jocs de proves
├── data/           # Fitxers de dades (txt, csv, bin)
├── docs/           # Documentació JavaDoc
├── lib/            # Llibreries externes (si escau)
├── README.md
└── .gitignore
```

## Funcionalitats implementades

### Aplicació per consola
L’aplicació per consola permet:

- Gestió de la data actual del sistema
- Consulta i visualització de:
  - Activitats
  - Usuaris
  - Inscripcions i llistes d’espera
- Inscripció i baixa d’usuaris a activitats
- Gestió de places limitades i llista d’espera
- Valoració d’activitats finalitzades
- Estadístiques i resums de valoracions
- Donar de baixa activitats segons els criteris establerts

Totes les opcions del menú definides a l’enunciat han estat implementades.

---

### Aplicació amb interfície gràfica
S’ha desenvolupat una interfície gràfica amb **Java Swing**, separada del codi de dades, que permet:

- Visualitzar les activitats actives d’un mes concret
- Mostrar les activitats en format calendari
- Filtrar per tipus d’activitat

---

## Persistència de dades

- Les **inscripcions** es guarden en un fitxer **serialitzat**
- La resta d’informació es guarda en **fitxers de text** (`;` com a separador)
- Les dades es carreguen automàticament a l’inici del programa
- En sortir, l’usuari pot decidir si vol desar els canvis

Els fitxers de prova estan inclosos al projecte i permeten validar totes les funcionalitats.

---

## Gestió d’excepcions
El projecte inclou:
- Control d’excepcions predefinides de Java
- Excepcions pròpies coherents amb el domini del problema
- Gestió d’errors com dates incorrectes, duplicats i manca de places

---

## Jocs de proves
S’han implementat jocs de proves per validar:
- El correcte funcionament de les classes principals
- El càlcul del dia de la setmana
- El tractament d’errors i excepcions

Les proves es troben en un package independent.