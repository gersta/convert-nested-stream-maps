# convert-nested-stream-maps
Find convenient ways to handle nested maps created by Java stream and convert them to more suitable POJOs if possible

Try to find ways in which to conveniently and smoothly handle Collectors.groupingBy within Java streams, which creates (nested) maps. These maps are 
usually hard to read if the levels of grouping exceed a hand full and also clutter the code a lot. Simple POJOs that represent the same
groupings would be more favorable
