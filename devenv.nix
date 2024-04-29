{ pkgs, lib, config, inputs, ... }:

{
  languages.java = {
    enable = true;
    jdk.package = pkgs.jdk17;
    maven.enable = true;
  };

  # https://devenv.sh/packages/
  packages = [ pkgs.git ];

  enterShell = ''
    hello
    git --version
  '';
}
