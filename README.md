# UiAutoMatorServer
Uiautomator server for find element
通过坐标定位控件,获取控件唯一属性
# 使用说明
使用三方包时，需要配置uidump.xml，否则编译不能通过
'''
 <target name="compile" depends="-build-setup, -pre-compile">
        <javac encoding="${java.encoding}"
                source="${java.source}" target="${java.target}"
                debug="true" extdirs="" includeantruntime="false"
                destdir="${out.classes.absolute.dir}"
                bootclasspathref="project.target.class.path"
                verbose="${verbose}"
                fork="${need.javac.fork}">
            <src path="${source.absolute.dir}" />
            <compilerarg line="${java.compilerargs}" />
            <classpath>
             <fileset dir="${jar.libs.dir}" includes="*.jar"></fileset>
            </classpath>
        </javac>
    </target>

 <target name="-dex" depends="compile, -post-compile">
        <dex executable="${dx}"
                output="${intermediate.dex.file}"
                nolocals="@{nolocals}"
                verbose="${verbose}">
                <fileset dir="${jar.libs.dir}">
                 <include name="jutf7-1.0.0.jar" />
                 <include name="log4j-1.2.17.jar" />
                </fileset>
            <path path="${out.classes.absolute.dir}"/>
        </dex>
    </target>
'''
