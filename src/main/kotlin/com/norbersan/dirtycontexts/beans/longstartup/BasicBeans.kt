package com.norbersan.dirtycontexts.beans.longstartup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import kotlin.random.Random

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
        status = Status.SETTINGUP
        TimeUnit.MILLISECONDS.sleep(setupMilis)
        status = Status.READY
        println("==> @PostConstruct finished for bean of type ${javaClass.simpleName}")
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

@Component
@Profile("longstartup")
class LongStartupBeanA() : AbstractLongStartupBean(){}

@Component
@Profile("longstartup")
class LongStartupBeanB() : AbstractLongStartupBean(){}

@Component
@Profile("longstartup")
class LongStartupBeanC() : AbstractLongStartupBean(){
}

@Component
@Profile("longstartup")
class LongStartupBeanContainer(){
    @Autowired
    lateinit var longStartupBeanA: LongStartupBeanA

    @Autowired
    lateinit var longStartupBeanB: LongStartupBeanB

    @Autowired
    lateinit var longStartupBeanC: LongStartupBeanC
}

enum class Status(){
    INSTANTIATING,
    INSTANTIATED,
    SETTINGUP,
    READY,
}