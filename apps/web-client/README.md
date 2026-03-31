## obodc Frontend Guide

English | [中文](docs/README_CN.md)

### Introduction

obodc has a centralized deployment **web version** and a locally run **client version**. The web version requires the obodc backend to run, while the client version installs its dependencies and generates a standalone package.

### Project Initialization

#### Environment Requirements

1. [Nodejs](https://nodejs.org/zh-cn/download) 16 or above
2. [pnpm](https://pnpm.io/zh/installation)
3. Minimum 8GB RAM
4. (Optional, required for generating Mac client) MacOS system

#### Client Dependency Installation (Skip for Web Version)

##### obodc Server

After packaging the obodc server jar, store it in the following directory:
[Build Jar](../../docs/en-US/DEVELOPER_GUIDE.md)

```shell
libraries
 - java
   - odc.jar
   - plugins
     - plugin-related packages
   - starters
     - starter-related packages
```

```shell
pnpm run prepack jar
```

##### JRE

```shell
# Use bundled JRE
pnpm run prepack jre
```

##### OBClient

```shell
# Use bundled OBClient
pnpm run prepack obclient
```

#### Install Dependencies

```shell
pnpm install
```

#### Configure obodc Server Address

Modify the `proxy` field in `config/config.js` and change the `target` attribute to the address of the obodc server.

```shell
proxy: {
    '/api/v1/webSocket/obclient': {
      target: 'obodc Server Address',
      ws: true,
    },
    '/api/': {
      target: 'obodc Server Address',
    },
    '/oauth2/': {
      target: 'obodc Server Address',
    },
    '/login/': {
      target: 'obodc Server Address',
    }
  }
```

### Development

#### Web Version Development

```shell
pnpm run dev
```

This will start a web server on the default `8000` port. Access [http://localhost:8000](https://localhost:8000) to open obodc.

#### Client Development

The obodc client is developed based on Electron. We need to start two services: the web server and the Electron server.

##### Start the Client Web Server

```shell
pnpm run dev:client
```

After it starts successfully, we can proceed to start Electron.

##### Start Electron

```shell
pnpm run start-electron
```

### Build

#### Web Version Build

```shell
pnpm run build:odc
```

The packaged artifacts can be found in `dist/renderer`.

#### Client Build

```shell
# Build for win, linux, mac
node ./scripts/client/build.js all
```

You can adjust the command parameters to selectively build different installation packages. Currently, the following types are supported:

1. **mac** - dmg installation package
2. **linux_x86** - x86_64 version deb, AppImage installation package
3. **linux_aarch64** - arm64 version deb, AppImage installation package
4. **win** - win32, win64 version installation package
5. **all** - all installation packages
