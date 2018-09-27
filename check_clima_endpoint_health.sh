#!/bin/bash

test_command=$(curl -s -o /dev/null -w "%{http_code}" localhost:8080/clima?dia=566)
if [ $test_command == "200" ] ;
then
    echo "OK :)" ;
else
    echo "KO :(" ;
fi
