# 자신의 일상을 기록으로 남길 수 있는 블로그 페이지

사진, 글을 함꼐 업로드하면서 개인이 남기고 싶은 추억을 기록할 수 있다.  
_이곳은 back-end 개발만 진행중이며 front-end 개발은 다른 팀원이 다른 repo에서 진행하고 있다._

### Used techniques

```
back-end : java, mariaDB, gradle, h2, jpa, mybatis
```

## Built With

* [spring](https://spring.io/) - The web framework used
* [springboot](https://spring.io/projects/spring-boot) - Spring based Applications
* [gradle](https://gradle.org/) - Dependency Management
* [git](https://github.com/seonghun127/cota) - Version(Configuration) Management

## CI & Deploy

* [travis-CI](https://travis-ci.org/) - Free CI services offered at github
* [S3 & CodeDeploy in AWS](https://aws.amazon.com)
  - **S3** : The repository where the file is stored  
  - **CodeDeploy** : IAM ROLE that deploys files automatically
                                                     
```
deploy:
  - provider: s3
    access_key_id: $AWS_ACCESS_KEY # Travis repo settings에 설정된 값
    secret_access_key: $AWS_SECRET_KEY # Travis repo settings에 설정된 값
    bucket: cota-deploy # S3 버킷
    region: ap-northeast-2
    skip_cleanup: true
    local_dir: deploy
    acl: public_read
    wait-until-deployed: true
    on:
      repo: seonghun127/cota #Github 주소
      branch: master
      
  - provider: codedeploy
    access_key_id: $AWS_ACCESS_KEY # Travis repo settings에 설정된 값
    secret_access_key: $AWS_SECRET_KEY # Travis repo settings에 설정된 값
    bucket: cota-deploy # S3 버킷
    key: cota.zip # 빌드 파일을 압축해서 전달
    bundle_type: zip
    application: cota # 웹 콘솔에서 등록한 CodeDeploy 어플리케이션
    deployment_group: cota # 웹 콘솔에서 등록한 CodeDeploy 배포 그룹
    region: ap-northeast-2
    wait-until-deployed: true
    on:
      repo: seonghun127/cota
      branch: master
```

<img width="600" alt="2019-01-27 7 38 32" src="https://user-images.githubusercontent.com/30451129/51799935-42daee80-226b-11e9-877e-379155432d45.png">

> [사진 출처](https://jojoldu.tistory.com/265?category=635883)

## Authors

* **김성훈** [github](https://github.com/seonghun127)  - **back-end 개발 담당** -
* **노태상** [github](https://github.com/nohtaesang)   - **front-end 개발 담당** -

## Roles

* 요구사항분석, 데이터베이스 구축, 화면 설계
* 게시글, 댓글 CRUD 구현
* 네아로 (네이버 아이디로 로그인)
