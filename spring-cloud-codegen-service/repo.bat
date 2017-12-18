@echo on
@echo =============================================================
@echo $                                                           $
@echo $                     Nepxion Skeleton                      $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  Nepxion Technologies All Right Reserved                  $
@echo $  Copyright(C) 2017                                        $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title Nepxion Skeleton
@color 0a

call mvn install:install-file -DgroupId=com.nepxion -DartifactId=skeleton-engine -Dversion=1.0.0 -Dfile=repo/com/nepxion/skeleton-engine/1.0.0/skeleton-engine-1.0.0.jar -Dpackaging=jar -DpomFile=repo/com/nepxion/skeleton-engine/1.0.0/skeleton-engine-1.0.0.pom