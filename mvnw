#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper Executable for Linux / Docker
# ----------------------------------------------------------------------------

which mvn >/dev/null 2>&1
if [ $? -eq 0 ]; then
    exec mvn "$@"
fi

if [ -f "/usr/share/maven/bin/mvn" ]; then
    exec /usr/share/maven/bin/mvn "$@"
fi

# Fallback: install maven or download via wrapper if needed
apt-get update -qq >/dev/null 2>&1 && apt-get install -y -qq maven >/dev/null 2>&1
exec mvn "$@"
