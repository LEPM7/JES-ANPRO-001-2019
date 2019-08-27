## JES-ANPRO-001-2019

El Ministerio Publico es una institución que cuenta con diversas fiscalías en todo el país,
para lo cual tiene la necesidad de contar con la información de la ubicación física de cada
una de ellas así como el número de teléfono. Información se deberá poder modificar en el
transcurso del tiempo ya que cambien de ubicación constantemente.

#### Requisitos
- Instalar docker
- Instalar docker-compose

#### Iniciar
- `docker-compose up`
- En otra terminal ejecutar 
`docker exec -ti sql-server-db "bash"` 
    - dentro del contenedor ejecutar `bash -c "/bin/bash sql.sh"`