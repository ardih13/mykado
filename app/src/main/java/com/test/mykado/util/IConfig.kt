package com.test.mykado.util

import java.io.File

interface IConfig {

    companion object{

        // folder
        var FOLDER_APP = "MyKado"
        var FOLDER_FOTO = FOLDER_APP

        var EXTENSION_FILE_FOTO = ".png"
        var FILE_SEPARATOR = "_"

        var FILE_NAME_MAIN: String? = "Registration"
        var FILE_NAME_FOTO: String = FOLDER_FOTO + File.separator + FILE_NAME_MAIN+ FILE_SEPARATOR
    }
}