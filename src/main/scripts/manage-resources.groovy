import groovy.util.XmlSlurper
import groovy.xml.XmlUtil

log.info "environment:$server"

if ('local'!=server){
    return;
}

File testXmlFile = "${project.basedir}/target/classes/teste.xml" as File
def xml = new XmlSlurper().parseText(testXmlFile.text)
def child2 = xml.child[1]
child2.localText().set(0,'xpto')
child2.@myattr = 'this is a new attribute'
def innerNode = new XmlSlurper().parseText('<replaced>this is my child replaced</replaced>')
child2.replaceBody innerNode

testXmlFile.withWriter {
    XmlUtil.serialize(xml, it)
}
