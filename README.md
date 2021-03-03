# seattle-library
シアトルライブラリのサンプル作成用

## git cloneする
まず、SSHで公開鍵を作成していない人は作成してください。
https://qiita.com/altblanc/items/2ddcfa68ece7a68aff3d

SSHでcloneする
https://qiita.com/dorara/items/942485e064f3e2bdd4f7


## gitコマンドチート表

### ブランチ作成してチェックアウトする
```
git checkout -b　feature/{ブランチ名}
```

### 編集した内容をaddする
```
git add .
```

### コミットする
```
git commit -m "コミットメッセージ記載"
```

### プッシュする
```
#作業ブランチで初回プッシュする時
git push -u origin HEAD

##2回目以降は下記でOK
git push
```


### 作業していた内容を一回退避させる
※最新のdevelopの内容を取り込む時とかに、作業内容は残しておきたいけど〜という時に使う
```
#退避する時
git stash
```
```
#退避したものをローカルに戻す時
git stash pop stash@{N} # N番目のスタッシュを適用し、削除する。
##最新のスタッシュの時は下記でOK
git stash pop stash@{0}
```

### 最新のdevelopを取り込む時
※退避したい作業内容はstashしとくこと！
```
# developにチェックアウト
git checkout develop
```
```
# リモートの最新のdevelopを取り込む
git pull 
```
```
# 作業していたブランチにチェックアウト
git checkout feature/{ブランチ名}
```
```
# developをマージ
git merge develop
```
※stashした内容があれば戻す。コンフリクトしたら解消する。


