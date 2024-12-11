package org.jetbrains.plugins.template

import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val whereTc1=executeCommand( "cmd /c npm --version")
    println(whereTc1)

}
fun executeCommand(command: String): String {
    return try {
        // Create a ProcessBuilder instance for the command
        val processBuilder = ProcessBuilder(*command.split(" ").toTypedArray())
            .redirectErrorStream(true)
         // Start the process
        val process = processBuilder.start()
        val output = process.inputStream.bufferedReader().readText()
        process.waitFor() // Wait for the process to complete
        output.trim()
            .replace("\\s+".toRegex(), " ") // Replace all unnecessary whitespaces with a single space
            .replace("RequestURL:", "\nRequest URL: ")
            .replace("Status:", "\nStatus: ")
            .replace("Response", "\n\nResponse:")
            .replace(",", ", ") // Add space after commas for readability
            .replace("\\n".toRegex(), "<br/>") // Use <br/> for line breaks in HTML
            .replace(":\\s+".toRegex(), ": ")
    } catch (e: Exception) {
        "Error executing command: ${e.message}"
    }
}
