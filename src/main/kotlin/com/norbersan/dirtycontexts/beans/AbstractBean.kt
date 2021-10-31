package com.norbersan.dirtycontexts.beans

import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import kotlin.random.Random

fun shortName(name: String) = name.split(".").toList().filter { it.contains("@") }.first()

abstract class AbstractBasicBean(){
    val id = UUID.randomUUID()

    override fun toString() = "==> bean ${javaClass.simpleName} ($id)"

    init {
        println("==> ${javaClass.simpleName} ($id) init in thread ${Thread.currentThread()}")
    }

    @PostConstruct
    fun setup(){
        println("==> ${javaClass.simpleName} ($id) @Postconstruct in thread ${Thread.currentThread()}")
    }

    @PreDestroy
    fun teardown(){
        println("==> ${javaClass.simpleName} ($id) @Predestroy in thread ${Thread.currentThread()}")
    }
}

abstract class AbstractLongInitBean(){
    val initMilis = Random.nextLong(500, 5000)
    var status = Status.INSTANTIATING
    init {
        println("==> init started for bean of type ${javaClass.simpleName}")
        TimeUnit.MILLISECONDS.sleep(initMilis)
        status = Status.INSTANTIATED
        println("==> init ended for bean of type ${javaClass.simpleName}")
    }
    override fun toString() = "bean ${javaClass.simpleName}, status: $status, startup: $initMilis milis"

    @PostConstruct
    fun setup(){
        status = Status.SETTING_UP
        println("==> @PostConstruct method called for bean of type ${javaClass.simpleName}")
        status = Status.READY
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

abstract class AbstractLongStartupBean(){
    val setupMilis = Random.nextLong(5000, 10000)
    var status = Status.INSTANTIATING
    init {
        status = Status.INSTANTIATED
    }
    override fun toString() = "bean ${javaClass.simpleName}, status: $status, setup: $setupMilis milis"

    @PostConstruct
    fun setup(){
        println("==> @PostConstruct started for bean of type ${javaClass.simpleName}")
        status = Status.SETTING_UP
        TimeUnit.MILLISECONDS.sleep(setupMilis)
        status = Status.READY
        println("==> @PostConstruct finished for bean of type ${javaClass.simpleName}")
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

abstract class AbstractAsyncInitBean(){
    val initMilis = Random.nextLong(5000, 10000)
    var status = Status.INSTANTIATING
    init {
        Thread{
            TimeUnit.MILLISECONDS.sleep(initMilis)
            status = Status.INSTANTIATED
        }.start()
    }

    override fun toString() = "bean ${javaClass.simpleName}, status: $status, startup: $initMilis milis"

    @PostConstruct
    fun setup(){
        status = Status.SETTING_UP
        println("==> @PostConstruct method called for bean of type ${javaClass.simpleName}")
        status = Status.READY
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

abstract class AbstractAsyncSetupBean(){
    val initMilis = Random.nextLong(5000, 10000)
    var status = Status.INSTANTIATING
    init {
        Thread{
            TimeUnit.MILLISECONDS.sleep(initMilis)
            status = Status.INSTANTIATED
        }.start()
    }
    override fun toString() = "bean ${javaClass.simpleName}, status: $status, startup: $initMilis milis"

    @PostConstruct
    fun setup(){
        println("==> @PostConstruct started for bean of type ${javaClass.simpleName}")
        status = Status.SETTING_UP
        Thread{
            TimeUnit.MILLISECONDS.sleep(initMilis)
            status = Status.READY
            println("==> @PostConstruct finished for bean of type ${javaClass.simpleName}")
        }.start()
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

enum class Status(){
    INSTANTIATING,
    INSTANTIATED,
    SETTING_UP,
    READY,
}