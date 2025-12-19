import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.annotation.*
import java.io.File
import java.io.StringReader

// 1. Определяем классы с аннотациями JAXB

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
data class Students(
    @XmlElement(name = "student")
    var students: List<Student> = mutableListOf()
) {
    constructor() : this(mutableListOf())
    
    override fun toString(): String {
        return students.joinToString("\n${"-".repeat(40)}\n") { it.toString() }
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
data class Student(
    @XmlElement(name = "firstName")
    var firstName: String = "",
    
    @XmlElement(name = "secondName")
    var secondName: String = "",
    
    @XmlElement(name = "age")
    var age: Int = 0,
    
    @XmlElement(name = "hobbies")
    var hobbies: Hobbies = Hobbies()
) {
    constructor() : this("", "", 0, Hobbies())
    
    override fun toString(): String {
        return """
            Студент:
              Имя: $firstName
              Фамилия: $secondName
              Возраст: $age
              Хобби: ${hobbies.hobbies.joinToString(", ")}
        """.trimIndent()
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
data class Hobbies(
    @XmlElement(name = "hobby")
    var hobbies: List<String> = mutableListOf()
) {
    constructor() : this(mutableListOf())
}

// 2. Основная программа

fun main() {
    println("=== JAXB XML Reader на Kotlin ===\n")
    
    try {
        // Создаем JAXB контекст
        val jaxbContext = JAXBContext.newInstance(Students::class.java)
        val unmarshaller = jaxbContext.createUnmarshaller()
        
        // Читаем XML файл
        val xmlFile = File("students.xml")
        
        if (xmlFile.exists()) {
            println("Читаем файл: ${xmlFile.absolutePath}\n")
            
            // Преобразуем XML в объекты
            val students = unmarshaller.unmarshal(xmlFile) as Students
            
            // Выводим результат
            println("=".repeat(50))
            println("ПРОЧИТАННЫЕ ДАННЫЕ ИЗ XML:")
            println("=".repeat(50))
            println()
            println(students)
            
            // Дополнительная информация
            println("\n" + "=".repeat(50))
            println("СТАТИСТИКА:")
            println("Всего студентов: ${students.students.size}")
            
            val totalAge = students.students.sumOf { it.age }
            val averageAge = totalAge.toDouble() / students.students.size
            println("Средний возраст: ${"%.1f".format(averageAge)}")
            
            val allHobbies = students.students.flatMap { it.hobbies.hobbies }
            println("Всего хобби: ${allHobbies.size}")
            println("Уникальные хобби: ${allHobbies.toSet().joinToString(", ")}")
            
        } else {
            println("Файл students.xml не найден!")
            println("Создайте файл students.xml в той же папке")
        }
        
    } catch (e: Exception) {
        println("Ошибка при чтении XML: ${e.message}")
        e.printStackTrace()
    }
}
