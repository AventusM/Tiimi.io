#!/usr/bin/env sh

##############################################################################
##
##  Tiimi.io start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/.." >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Tiimi.io"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and TIIMI_IO_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn ( ) {
    echo "$*"
}

die ( ) {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

CLASSPATH=$APP_HOME/lib/Tiimi.io.jar:$APP_HOME/lib/sqlite-jdbc-3.7.2.jar:$APP_HOME/lib/spark-core-2.7.1.jar:$APP_HOME/lib/spark-template-thymeleaf-2.7.1.jar:$APP_HOME/lib/twilio-java-sdk-7.0.0-rc-10.jar:$APP_HOME/lib/selenium-firefox-driver-3.4.0.jar:$APP_HOME/lib/selenium-java-3.4.0.jar:$APP_HOME/lib/selenium-server-3.4.0.jar:$APP_HOME/lib/slf4j-api-1.7.13.jar:$APP_HOME/lib/jetty-server-9.4.6.v20170531.jar:$APP_HOME/lib/jetty-webapp-9.4.6.v20170531.jar:$APP_HOME/lib/websocket-server-9.4.6.v20170531.jar:$APP_HOME/lib/websocket-servlet-9.4.6.v20170531.jar:$APP_HOME/lib/thymeleaf-3.0.5.RELEASE.jar:$APP_HOME/lib/thymeleaf-extras-java8time-3.0.0.RELEASE.jar:$APP_HOME/lib/joda-time-2.5.jar:$APP_HOME/lib/jjwt-0.4.jar:$APP_HOME/lib/async-http-client-1.8.14.jar:$APP_HOME/lib/jackson-core-2.4.3.jar:$APP_HOME/lib/jackson-annotations-2.4.3.jar:$APP_HOME/lib/jackson-databind-2.4.3.jar:$APP_HOME/lib/jaxb-api-2.2.jar:$APP_HOME/lib/cglib-nodep-3.2.4.jar:$APP_HOME/lib/commons-exec-1.3.jar:$APP_HOME/lib/commons-codec-1.10.jar:$APP_HOME/lib/commons-logging-1.2.jar:$APP_HOME/lib/httpcore-4.4.6.jar:$APP_HOME/lib/jna-4.1.0.jar:$APP_HOME/lib/jna-platform-4.1.0.jar:$APP_HOME/lib/selenium-chrome-driver-3.4.0.jar:$APP_HOME/lib/selenium-edge-driver-3.4.0.jar:$APP_HOME/lib/selenium-ie-driver-3.4.0.jar:$APP_HOME/lib/selenium-opera-driver-3.4.0.jar:$APP_HOME/lib/selenium-safari-driver-3.4.0.jar:$APP_HOME/lib/commons-lang3-3.5.jar:$APP_HOME/lib/commons-io-2.5.jar:$APP_HOME/lib/sac-1.3.jar:$APP_HOME/lib/cssparser-0.9.22.jar:$APP_HOME/lib/hamcrest-core-1.3.jar:$APP_HOME/lib/htmlunit-2.26.jar:$APP_HOME/lib/htmlunit-core-js-2.26.jar:$APP_HOME/lib/neko-htmlunit-2.25.jar:$APP_HOME/lib/httpmime-4.5.3.jar:$APP_HOME/lib/junit-4.12.jar:$APP_HOME/lib/phantomjsdriver-1.4.0.jar:$APP_HOME/lib/htmlunit-driver-2.26.jar:$APP_HOME/lib/javax.servlet-api-3.1.0.jar:$APP_HOME/lib/serializer-2.7.2.jar:$APP_HOME/lib/xalan-2.7.2.jar:$APP_HOME/lib/xercesImpl-2.11.0.jar:$APP_HOME/lib/xml-apis-1.4.01.jar:$APP_HOME/lib/jcommander-1.48.jar:$APP_HOME/lib/jcip-annotations-1.0.jar:$APP_HOME/lib/jetty-repacked-9.4.1.v20170120.jar:$APP_HOME/lib/snakeyaml-1.15.jar:$APP_HOME/lib/ognl-3.1.12.jar:$APP_HOME/lib/attoparser-2.0.3.RELEASE.jar:$APP_HOME/lib/unbescape-1.1.4.RELEASE.jar:$APP_HOME/lib/netty-3.9.2.Final.jar:$APP_HOME/lib/stax-api-1.0-2.jar:$APP_HOME/lib/activation-1.1.jar:$APP_HOME/lib/jetty-xml-9.4.6.v20170531.jar:$APP_HOME/lib/jetty-servlet-9.4.6.v20170531.jar:$APP_HOME/lib/jetty-security-9.4.6.v20170531.jar:$APP_HOME/lib/httpclient-4.5.3.jar:$APP_HOME/lib/javassist-3.20.0-GA.jar:$APP_HOME/lib/selenium-api-3.7.1.jar:$APP_HOME/lib/selenium-support-3.7.1.jar:$APP_HOME/lib/byte-buddy-1.7.5.jar:$APP_HOME/lib/jetty-util-9.4.6.v20170531.jar:$APP_HOME/lib/jetty-io-9.4.6.v20170531.jar:$APP_HOME/lib/jetty-http-9.4.6.v20170531.jar:$APP_HOME/lib/websocket-common-9.4.6.v20170531.jar:$APP_HOME/lib/websocket-client-9.4.6.v20170531.jar:$APP_HOME/lib/websocket-api-9.4.6.v20170531.jar:$APP_HOME/lib/selenium-remote-driver-3.7.1.jar:$APP_HOME/lib/gson-2.8.2.jar:$APP_HOME/lib/guava-23.0.jar:$APP_HOME/lib/jsr305-1.3.9.jar:$APP_HOME/lib/error_prone_annotations-2.0.18.jar:$APP_HOME/lib/j2objc-annotations-1.1.jar:$APP_HOME/lib/animal-sniffer-annotations-1.14.jar:$APP_HOME/lib/jetty-client-9.4.6.v20170531.jar

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if [ "$cygwin" = "false" -a "$darwin" = "false" -a "$nonstop" = "false" ] ; then
    MAX_FD_LIMIT=`ulimit -H -n`
    if [ $? -eq 0 ] ; then
        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then
            MAX_FD="$MAX_FD_LIMIT"
        fi
        ulimit -n $MAX_FD
        if [ $? -ne 0 ] ; then
            warn "Could not set maximum file descriptor limit: $MAX_FD"
        fi
    else
        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# For Darwin, add options to specify how the application appears in the dock
if $darwin; then
    GRADLE_OPTS="$GRADLE_OPTS \"-Xdock:name=$APP_NAME\" \"-Xdock:icon=$APP_HOME/media/gradle.icns\""
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin ; then
    APP_HOME=`cygpath --path --mixed "$APP_HOME"`
    CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`
    JAVACMD=`cygpath --unix "$JAVACMD"`

    # We build the pattern for arguments to be converted via cygpath
    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`
    SEP=""
    for dir in $ROOTDIRSRAW ; do
        ROOTDIRS="$ROOTDIRS$SEP$dir"
        SEP="|"
    done
    OURCYGPATTERN="(^($ROOTDIRS))"
    # Add a user-defined pattern to the cygpath arguments
    if [ "$GRADLE_CYGPATTERN" != "" ] ; then
        OURCYGPATTERN="$OURCYGPATTERN|($GRADLE_CYGPATTERN)"
    fi
    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    i=0
    for arg in "$@" ; do
        CHECK=`echo "$arg"|egrep -c "$OURCYGPATTERN" -`
        CHECK2=`echo "$arg"|egrep -c "^-"`                                 ### Determine if an option

        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition
            eval `echo args$i`=`cygpath --path --ignore --mixed "$arg"`
        else
            eval `echo args$i`="\"$arg\""
        fi
        i=$((i+1))
    done
    case $i in
        (0) set -- ;;
        (1) set -- "$args0" ;;
        (2) set -- "$args0" "$args1" ;;
        (3) set -- "$args0" "$args1" "$args2" ;;
        (4) set -- "$args0" "$args1" "$args2" "$args3" ;;
        (5) set -- "$args0" "$args1" "$args2" "$args3" "$args4" ;;
        (6) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" ;;
        (7) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" ;;
        (8) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" ;;
        (9) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" "$args8" ;;
    esac
fi

# Escape application args
save ( ) {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}
APP_ARGS=$(save "$@")

# Collect all arguments for the java command, following the shell quoting and substitution rules
eval set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $TIIMI_IO_OPTS -classpath "\"$CLASSPATH\"" ohtu.Main "$APP_ARGS"

# by default we should be in the correct project dir, but when run from Finder on Mac, the cwd is wrong
if [ "$(uname)" = "Darwin" ] && [ "$HOME" = "$PWD" ]; then
  cd "$(dirname "$0")"
fi

exec "$JAVACMD" "$@"