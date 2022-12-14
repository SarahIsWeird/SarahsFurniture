plugins {
    id("fabric-loom")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}

base {
    val archivesBaseName: String by project
    archivesName.set(archivesBaseName)
}

val modVersion: String by project
version = modVersion

val mavenGroup: String by project
group = mavenGroup

repositories {
    maven("https://maven.wispforest.io")
    maven("https://storage.googleapis.com/devan-maven/")
    maven("https://maven.shedaniel.me")
}

dependencies {
    val minecraftVersion: String by project
    val yarnMappings: String by project

    val loaderVersion: String by project
    val fabricVersion: String by project
    val fabricKotlinVersion: String by project

    val clothConfigVersion: String by project

    val owoVersion: String by project
    val condensedCreativeVersion: String by project

    val arrpVersion: String by project

    minecraft("com.mojang", "minecraft", minecraftVersion)
    mappings("net.fabricmc", "yarn", yarnMappings, null, "v2")

    modImplementation("net.fabricmc", "fabric-loader", loaderVersion)
    modImplementation("net.fabricmc.fabric-api", "fabric-api", fabricVersion)
    modImplementation("net.fabricmc", "fabric-language-kotlin", fabricKotlinVersion)

    // Needed for Condensed Creative
    modApi("me.shedaniel.cloth", "cloth-config-fabric", clothConfigVersion) {
        exclude(group = "net.fabricmc.fabric-api")
    }

    modImplementation("io.wispforest", "owo-lib", owoVersion)
    modImplementation("io.wispforest", "condensed_creative-fabric", condensedCreativeVersion)

    modImplementation("net.devtech", "arrp", arrpVersion)
}

tasks {
    val javaVersion = JavaVersion.VERSION_17

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions { jvmTarget = javaVersion.toString() }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${base.archivesName}" }
        }
    }

    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") { expand(mutableMapOf("version" to project.version)) }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
        }

        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        withSourcesJar()
    }
}
