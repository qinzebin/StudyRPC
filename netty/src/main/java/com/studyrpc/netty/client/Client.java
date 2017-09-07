package com.studyrpc.netty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * netty4的客户端
 * @author 20141022
 *
 */
public class Client {
	public static void main(String[] args) {
		//服务类
		Bootstrap bootstrap = new Bootstrap();
		
		//worker
		EventLoopGroup worker = new NioEventLoopGroup();
		
		try{
			//设置线程池
			bootstrap.group(worker);
			
			//设置socket通道工厂
			bootstrap.channel(NioSocketChannel.class);
			
			//设置管道
			bootstrap.handler(new ChannelInitializer<Channel>(){

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new StringEncoder());
					ch.pipeline().addLast(new ClientHandler());
				}
				
			});
			
			ChannelFuture future = bootstrap.connect("127.0.0.1", 10101);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				System.out.println("请输入");
				String msg = bufferedReader.readLine();
				future.channel().writeAndFlush(msg);
			}
		} catch(Exception e){
			
		} finally{
			worker.shutdownGracefully();
		}
	}
}
