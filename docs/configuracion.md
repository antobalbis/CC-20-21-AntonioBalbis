# Configuración de Git
Lo primero es crear un perfil de GitHub a través de la [web](https://github.com). Luego descargamos git desde la consola y añadimos el nombre de usuario y la dirección e-mail con los siguientes comandos.

git config --global user.name = "antobalbis"
git config --global user.email = "antoniobalh@gmail.com"

Luego vamos al perfil de github en la web para cambiar la imagen por defecto por una fotografía.

![imagen 1](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/conf-git/imagenes/Captura%20de%20pantalla%20de%202020-10-18%2019-44-05.png)

Ahora continuamos creando un par de claves pública/privada para poder acceder a github sin necesidad de introducir la contraseña. Las claves se generan en el equipo local con el comando *ssh-keygen*, luego añadimos la clave pública *.pub* a github.

![imagen 2](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/conf-git/imagenes/Captura%20de%20pantalla%20de%202020-10-18%2019-58-41.png)

Ahora configuramos git en la máquina local para que siempre que se haga un pull se haga --rebase. Esto lo hacemos con el comando *git config --global pull.rebase true*.

Pur último añadimos la autentificación en dos pasos, para ello en el perfil de github entramos en *security account* y activamos la autenticación en dos pasos, que nos preguntará si queremos que sea mediante aplicación o sms, en este caso se ha seleccionado sms.