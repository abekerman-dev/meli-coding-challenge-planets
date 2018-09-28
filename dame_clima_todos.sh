#!/bin/bash

for i in {1..360}
do
	curl localhost:8080/clima?dia=${i}
	printf "\n"
done
