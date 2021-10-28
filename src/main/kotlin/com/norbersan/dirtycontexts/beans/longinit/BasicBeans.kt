package com.norbersan.dirtycontexts.beans.longinit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import kotlin.random.Random

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
        status = Status.SETTINGUP
        println("==> @PostConstruct method called for bean of type ${javaClass.simpleName}")
        status = Status.READY
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

@Component
@Profile("longinit")
class LongInitBeanA() : AbstractLongInitBean(){}

@Component
@Profile("longinit")
class LongInitBeanB() : AbstractLongInitBean(){}

@Component
@Profile("longinit")
class LongInitBeanC() : AbstractLongInitBean(){
}

@Component
@Profile("longinit")
class LongInitBeanContainer(){
    @Autowired
    lateinit var longInitBeanA: LongInitBeanA

    @Autowired
    lateinit var longInitBeanB: LongInitBeanB

    @Autowired
    lateinit var longInitBeanC: LongInitBeanC
}

enum class Status(){
    INSTANTIATING,
    INSTANTIATED,
    SETTINGUP,
    READY,
}