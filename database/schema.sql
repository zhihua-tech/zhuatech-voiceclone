-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/
CREATE DATABASE IF NOT EXISTS zhuatech_voiceclone DEFAULT CHARACTER SET utf8mb4;
USE zhuatech_voiceclone;
CREATE TABLE voice_profile (id BIGINT PRIMARY KEY AUTO_INCREMENT, profile_name VARCHAR(100) NOT NULL, language_code VARCHAR(20) NOT NULL, authorization_reference VARCHAR(255) NOT NULL, provider_code VARCHAR(60) NOT NULL DEFAULT 'local', status VARCHAR(30) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
CREATE TABLE synthesis_job (id BIGINT PRIMARY KEY AUTO_INCREMENT, profile_id BIGINT NOT NULL, target_text TEXT NOT NULL, watermark_enabled BOOLEAN NOT NULL DEFAULT TRUE, job_status VARCHAR(30) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, INDEX idx_job_profile_status(profile_id,job_status));

