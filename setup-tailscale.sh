#!/bin/bash

# 设置 Tailscale 认证密钥
TAILSCALE_AUTHKEY = "tskey-auth-kyJ7wP3CNTRL-8K6V6HYoP4ancTGh8gLJ5aovx4BYVxQac"
# 启动 Docker 服务
#sudo service docker start

# 启动 Tailscale 并进行自动登录
sudo tailscale up --authkey $TAILSCALE_AUTHKEY --hostname gitpod-workspace

# 配置 Tailscale IP 保持稳定
#sudo tailscale ip -4 | sudo tee /etc/tailscale/ip

# 添加 tailscale IP 到 /etc/hosts
#TAILSCALE_IP=$(cat /etc/tailscale/ip)
#echo "$TAILSCALE_IP gitpod-workspace" | sudo tee -a /etc/hosts