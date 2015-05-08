# toyota-jgroups

## Executive Summary
A middle tier, which provides distributed-querying API, has been developed for the vehicular clouds. The middletier uses Jgroups(www.jgroups.org) for establishing a multivehicle cluster using ipv4 multicasting. Once a node joins the cluster it can query other nodes for information using shared classes.

## Assumptions
1. Assumed that applications along with the middle-tier jars will run in the same JVM.
2. An IP Address is allocated to the vehicle in the cloud.

## Installation Steps
Please refer to:
`documents/installation-guide.docx`

## Contributors
Hyo Jeong: hyo.jeong@sv.cmu.edu

Ambarish Karole: ambarish.karole@sv.cmu.edu

Grace Lee: grace.lee@sv.cmu.edu

AJ Ruiz: aj.ruiz@sv.cmu.edu

Harikumar Sulochana: harikumar.kumar.sulochana@sv.cmu.edu

